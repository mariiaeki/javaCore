package homework_15_2001_concurrency.storage.initor.fileinitor;

import homework_15_2001_concurrency.cargo.domain.BasicCargo;
import homework_15_2001_concurrency.cargo.domain.CargoType;
import homework_15_2001_concurrency.cargo.domain.OutfitCargo;
import homework_15_2001_concurrency.cargo.domain.PerishableCargo;
import homework_15_2001_concurrency.carrier.domain.Carrier;
import homework_15_2001_concurrency.carrier.domain.CarrierType;
import homework_15_2001_concurrency.common.company_exceptions.checked.InitStorageException;
import homework_15_2001_concurrency.common.util.FileUtils;
import homework_15_2001_concurrency.common.util.JavaUtilDateUtils;
import homework_15_2001_concurrency.common.util.XMLDomUtils;
import homework_15_2001_concurrency.storage.IdGenerator;
import homework_15_2001_concurrency.transportation.domain.Transportation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static homework_13_3012.common.util.XMLDomUtils.getOnlyElement;
import static homework_13_3012.common.util.XMLDomUtils.getOnlyElementTextContent;

public class StorageInitorInXML extends BaseStorageInitorInFile {
    private static final String FILE = "/xmldata.xml";

    private static Map<String, BasicCargo> cargoMap;
    private static Map<String, Carrier> carrierMap;
    private static List<ParsedTransportation> transportations;

    @Override
    public void storageInitor() throws InitStorageException, IOException {
        //File file = null;
        final File file = FileUtils.createFileFromResource("init-data", "init-data-file", FILE);;

        try {
            //file = getFileWithInitData();
            final Document document = XMLDomUtils.getDocument(file);

            Thread thread_cargo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cargoMap = parseCargos(document);
                        persistCargos(cargoMap.values());

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread thread_carrier = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        carrierMap = parseCarriers(document);
                        persistCarriers(carrierMap.values());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread thread_trs = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        transportations = parseTransportationsData(document);
//
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            });

            thread_cargo.start();
            thread_carrier.start();
            thread_trs.start();

            thread_cargo.join();
            thread_carrier.join();
            thread_trs.join();

            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("xmldata", "xmldata-file", FILE);
    }

    private Map<String, BasicCargo> parseCargos(Document doc) throws ParseException {
        Map<String, BasicCargo> cargos = new LinkedHashMap<>();

        Element root = getOnlyElement(doc, "cargos");
        NodeList xmlCargos = root.getElementsByTagName("cargo");

        for (int i = 0; i < xmlCargos.getLength(); i++) {
            Map.Entry<String, BasicCargo> parsedData = parseCargo(xmlCargos.item(i));
            cargos.put(parsedData.getKey(), parsedData.getValue());
        }

        return cargos;
    }

    private Map.Entry<String, BasicCargo> parseCargo(org.w3c.dom.Node cargoXml) throws ParseException {
        Element cargoElem = ((Element) cargoXml);

        String id = cargoElem.getAttribute("id");
        CargoType cargoType = CargoType.valueOf(cargoElem.getAttribute("type"));

        BasicCargo cargo;
        if (CargoType.PERISHABLE.equals(cargoType)) {
            PerishableCargo foodCargo = new PerishableCargo();
            Date expirationDate = JavaUtilDateUtils
                    .valueOf(getOnlyElementTextContent(cargoElem, "expirationDate"));
            foodCargo.setDateOfExpire(expirationDate);
            foodCargo.setStoreTemperature(
                    Integer.parseInt(getOnlyElementTextContent(cargoElem, "storeTemperature")));
            cargo = foodCargo;
        } else {
            OutfitCargo clothersCargo = new OutfitCargo();
            clothersCargo.setGender(OutfitCargo.Gender.valueOf(getOnlyElementTextContent(cargoElem, "gender")));
            clothersCargo.setSize(Integer.parseInt(getOnlyElementTextContent(cargoElem, "size")));
            cargo = clothersCargo;
        }

        cargo.setId(IdGenerator.generateId());

        cargo.setName(getOnlyElementTextContent(cargoElem, "name"));
        cargo.setWeight(Integer.parseInt(getOnlyElementTextContent(cargoElem, "weight")));
        cargo.setCargoType(cargoType);

        return new SimpleEntry<>(id, cargo);
    }

    private Map<String, Carrier> parseCarriers(Document doc) throws ParseException {
        Map<String, Carrier> carriers = new LinkedHashMap<>();

        Element root = getOnlyElement(doc, "carriers");
        NodeList xmlCarriers = root.getElementsByTagName("carrier");

        for (int i = 0; i < xmlCarriers.getLength(); i++) {
            Map.Entry<String, Carrier> parsedData = parseCarrier(xmlCarriers.item(i));
            carriers.put(parsedData.getKey(), parsedData.getValue());
        }

        return carriers;
    }


    private Map.Entry<String, Carrier> parseCarrier(org.w3c.dom.Node cargoXml) {
        Element carrierElement = ((Element) cargoXml);

        String id = carrierElement.getAttribute("id");
        Carrier carrier = new Carrier();

        carrier.setId(IdGenerator.generateId());


        carrier.setName(getOnlyElementTextContent(carrierElement, "name"));
        carrier.setAddress(getOnlyElementTextContent(carrierElement, "address"));
        String carrierTypeStr = getOnlyElementTextContent(carrierElement, "type");
        carrier.setCarrierType(CarrierType.valueOf(carrierTypeStr));

        return new SimpleEntry<>(id, carrier);
    }

    private List<ParsedTransportation> parseTransportationsData(Document doc) throws ParseException {
        List<ParsedTransportation> result = new ArrayList<>();

        Element root = getOnlyElement(doc, "transportations");
        NodeList xmlTransportations = root.getElementsByTagName("transportation");

        for (int i = 0; i < xmlTransportations.getLength(); i++) {
            ParsedTransportation parsedData = parseTransportationData(xmlTransportations.item(i));
            result.add(parsedData);
        }

        return result;
    }

    private ParsedTransportation parseTransportationData(org.w3c.dom.Node transportationXml)
            throws ParseException {
        Element transportationElement = ((Element) transportationXml);

        ParsedTransportation result = new ParsedTransportation();
        String cargoRef = transportationElement.getAttribute("cargoref");
        result.setCargoRef(cargoRef);
        String carrierRef = transportationElement.getAttribute("carrierref");
        result.setCarrierRef(carrierRef);

        Transportation transportation = new Transportation();
        transportation.setId(IdGenerator.generateId());
        transportation.setBillTo(getOnlyElementTextContent(transportationElement, "billto"));
        transportation.setDescription(getOnlyElementTextContent(transportationElement, "description"));
        String beginDataStr = getOnlyElementTextContent(transportationElement, "transportationBeginDate");
        transportation.setDate(JavaUtilDateUtils.valueOf(beginDataStr));
        result.setTransportation(transportation);

        return result;
    }

}
