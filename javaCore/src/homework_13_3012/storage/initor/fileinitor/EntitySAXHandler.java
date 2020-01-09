package homework_13_3012.storage.initor.fileinitor;

import homework_13_3012.cargo.domain.BasicCargo;
import homework_13_3012.cargo.domain.CargoType;
import homework_13_3012.cargo.domain.OutfitCargo;
import homework_13_3012.cargo.domain.PerishableCargo;
import homework_13_3012.carrier.domain.Carrier;
import homework_13_3012.carrier.domain.CarrierType;
import homework_13_3012.common.util.JavaUtilDateUtils;
import homework_13_3012.storage.IdGenerator;
import homework_13_3012.transportation.domain.Transportation;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class EntitySAXHandler extends DefaultHandler {
    private Map<String, BasicCargo> parsedCargos;
    private PerishableCargo perishableCargo;
    private OutfitCargo outfitCargo;
    private String id, type, name, weight, size, date, temperature, gender;

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

            case "transportation" : {
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
                if (type.equals("CARRIER")){
                    curCarrier.setName(data);
                }else {
                    name = data;
                }
                break;
            }

            case "weight": {
                weight = data;
                break;
            }

            case "size": {
                size = data;
                break;
            }

            case "gender": {
                gender = data;
                break;
            }

            case "storeTemperature": {
                temperature = data;
                break;
            }

            case "expirationDate": {
                date = data;
                break;
            }

            case "cargo": {
                if (type.equals("PERISHABLE")){
                    perishableCargo = new PerishableCargo();
                    perishableCargo.setId(IdGenerator.generateId());
                    perishableCargo.setName(name);
                    perishableCargo.setWeight(Integer.parseInt(weight));
                    try {
                        perishableCargo.setDateOfExpire(JavaUtilDateUtils.valueOf(date));
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    perishableCargo.setStoreTemperature(Integer.parseInt(temperature));
                    perishableCargo.setCargoType(CargoType.valueOf(type));

                    parsedCargos.put(id, perishableCargo);
                    break;

                }else {
                    outfitCargo = new OutfitCargo();
                    outfitCargo.setId(IdGenerator.generateId());
                    outfitCargo.setName(name);
                    outfitCargo.setWeight(Integer.parseInt(weight));
                    outfitCargo.setSize(Integer.valueOf(size));
                    outfitCargo.setGender(OutfitCargo.Gender.valueOf(gender));
                    outfitCargo.setCargoType(CargoType.valueOf(type));

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

            case "billto":{
                curTrs.setBillTo(data);
                break;
            }

            case "transportationBeginDate": {
                try {
                    curTrs.setDate(JavaUtilDateUtils.valueOf(data));
                }catch (ParseException e){
                    e.printStackTrace();
                }
                break;
            }

            case "description": {
                curTrs.setDescription(data);
                break;
            }

            case "transportation":{
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
