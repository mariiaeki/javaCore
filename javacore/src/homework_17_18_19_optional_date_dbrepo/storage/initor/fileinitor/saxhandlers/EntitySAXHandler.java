package homework_17_18_19_optional_date_dbrepo.storage.initor.fileinitor.saxhandlers;

import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.CargoType;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.OutfitCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.PerishableCargo;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.CarrierType;
import homework_17_18_19_optional_date_dbrepo.storage.IdGenerator;
import homework_17_18_19_optional_date_dbrepo.storage.initor.fileinitor.BaseStorageInitorInFile;
import homework_17_18_19_optional_date_dbrepo.transportation.domain.Transportation;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class EntitySAXHandler extends DefaultHandler {
    private Map<String, BasicCargo> parsedCargos;
    private PerishableCargo perishableCargo;
    private OutfitCargo outfitCargo;
    private String id, type;

    private Map<String, Carrier> parsedCarriers;
    private Carrier curCarrier;

    private List<BaseStorageInitorInFile.ParsedTransportation> parsedTransportations;
    private BaseStorageInitorInFile.ParsedTransportation curParsedTrs;
    private Transportation curTrs;

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws org.xml.sax.SAXException {
        stringBuilder.setLength(0);

        switch (qName) {

            case "cargos": {
                parsedCargos = new LinkedHashMap<>();
                break;
            }

            case "cargo": {
                if (attributes.getValue("type").equals("PERISHABLE")) {
                    perishableCargo = new PerishableCargo();
                    perishableCargo.setCargoType(CargoType.valueOf(attributes.getValue("type")));
                    perishableCargo.setId(IdGenerator.generateId());
                } else {
                    outfitCargo = new OutfitCargo();
                    outfitCargo.setCargoType(CargoType.valueOf(attributes.getValue("type")));
                    outfitCargo.setId(IdGenerator.generateId());
                }
                type = attributes.getValue("type");
                id = attributes.getValue("id");
                break;
            }

            case "carriers": {
                parsedCarriers = new LinkedHashMap<>();
                break;
            }

            case "carrier": {
                id = attributes.getValue("id");
                type = "CARRIER";
                curCarrier = new Carrier();
                curCarrier.setId(IdGenerator.generateId());
                break;
            }

            case "transportations": {
                parsedTransportations = new ArrayList<>();
                break;
            }

            case "transportation": {
                curParsedTrs = new BaseStorageInitorInFile.ParsedTransportation();
                curParsedTrs.setCargoRef(attributes.getValue("cargoref"));
                curParsedTrs.setCarrierRef(attributes.getValue("carrierref"));

                curTrs = new Transportation();
                curTrs.setId(IdGenerator.generateId());
                break;
            }

        }
    }

    @Override
    public void endElement(String s, String s1, String qName) {
        String data = stringBuilder.toString();
        switch (qName) {

            case "name": {
                if (type.equals("CARRIER")) {
                    curCarrier.setName(data);
                } else if (type.equals("PERISHABLE")) {
                    perishableCargo.setName(data);
                } else {
                    outfitCargo.setName(data);
                }
                break;
            }

            case "weight": {
                if (type.equals("PERISHABLE")) {
                    perishableCargo.setWeight(Integer.parseInt(data));
                } else {
                    outfitCargo.setWeight(Integer.parseInt(data));
                }
                break;
            }

            case "size": {
                outfitCargo.setSize(Integer.parseInt(data));
                break;
            }

            case "gender": {
                outfitCargo.setGender(OutfitCargo.Gender.valueOf(data));
                break;
            }

            case "storeTemperature": {
                perishableCargo.setStoreTemperature(Integer.parseInt(data));
                break;
            }

            case "expirationDate": {
                perishableCargo.setDateOfExpire(LocalDate.parse(data));

                break;
            }

            case "cargo": {
                if (type.equals("PERISHABLE")) {
                    parsedCargos.put(id, perishableCargo);
                    break;
                } else {
                    parsedCargos.put(id, outfitCargo);
                    break;
                }
            }

            case "address": {
                curCarrier.setAddress(data);
                break;
            }

            case "type": {
                curCarrier.setCarrierType(CarrierType.valueOf(data));
                break;
            }

            case "carrier": {
                parsedCarriers.put(id, curCarrier);
                break;
            }

            case "billto": {
                curTrs.setBillTo(data);
                break;
            }

            case "transportationBeginDate": {
                curTrs.setDate(LocalDateTime.parse(data));

                break;
            }

            case "description": {
                curTrs.setDescription(data);
                break;
            }

            case "transportation": {
                curParsedTrs.setTransportation(curTrs);
                parsedTransportations.add(curParsedTrs);
                break;
            }

        }
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        String data = new String(chars, start, length);
        stringBuilder.append(data);
    }

    public Map<String, BasicCargo> getParsedCargos() {
        return parsedCargos;
    }

    public Map<String, Carrier> getParsedCarriers() {
        return parsedCarriers;
    }

    public List<BaseStorageInitorInFile.ParsedTransportation> getParsedTransportations() {
        return parsedTransportations;
    }
}
