package homework_17_18_19_optional_date_dbrepo.storage.initor.fileinitor;

import homework_17_18_19_optional_date_dbrepo.common.util.FileUtils;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.CargoType;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.OutfitCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.PerishableCargo;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.CarrierType;
import homework_17_18_19_optional_date_dbrepo.common.company_exceptions.checked.InitStorageException;
import homework_17_18_19_optional_date_dbrepo.common.util.XMLDomUtils;
import homework_17_18_19_optional_date_dbrepo.storage.IdGenerator;
import homework_17_18_19_optional_date_dbrepo.transportation.domain.Transportation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import static homework_13_3012.common.util.XMLDomUtils.getOnlyElement;
import static homework_13_3012.common.util.XMLDomUtils.getOnlyElementTextContent;

public class StorageInitorInXML extends BaseStorageInitorInFile {
    private static final String FILE = "/xmldata.xml";


    @Override
    public void storageInitor() throws InitStorageException {
        File file = null;
        try {
            file = getFileWithInitData();
            Document document = XMLDomUtils.getDocument(file);
            Map<String, BasicCargo> cargoMap = parseCargos(document);
            Map<String, Carrier> carrierMap = parseCarriers(document);
            List<ParsedTransportation> transportations = parseTransportationsData(document);
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
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
            LocalDate expirationDate = LocalDate.parse(getOnlyElementTextContent(cargoElem, "expirationDate"));
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


    private Map.Entry<String, Carrier> parseCarrier(org.w3c.dom.Node  cargoXml) {
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
        transportation.setDate(LocalDateTime.parse(beginDataStr));
        result.setTransportation(transportation);

        return result;
    }

}
