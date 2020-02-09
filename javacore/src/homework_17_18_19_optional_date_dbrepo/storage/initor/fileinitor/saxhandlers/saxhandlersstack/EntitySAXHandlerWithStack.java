package homework_17_18_19_optional_date_dbrepo.storage.initor.fileinitor.saxhandlers.saxhandlersstack;

import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.CargoType;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.OutfitCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.PerishableCargo;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.CarrierType;
import homework_17_18_19_optional_date_dbrepo.storage.IdGenerator;
import homework_17_18_19_optional_date_dbrepo.storage.initor.fileinitor.BaseStorageInitorInFile;
import homework_17_18_19_optional_date_dbrepo.storage.initor.fileinitor.saxhandlers.saxhandlersstack.saxstackpaths.CargoPaths;
import homework_17_18_19_optional_date_dbrepo.storage.initor.fileinitor.saxhandlers.saxhandlersstack.saxstackpaths.CarrierPaths;
import homework_17_18_19_optional_date_dbrepo.storage.initor.fileinitor.saxhandlers.saxhandlersstack.saxstackpaths.TransportationPaths;
import homework_17_18_19_optional_date_dbrepo.transportation.domain.Transportation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class EntitySAXHandlerWithStack extends DefaultHandler {
    private StringBuilder content = new StringBuilder();

    private Map<String, BasicCargo> parsedCargos = new LinkedHashMap<>();
    private BasicCargo currentCargo;

    private Map<String, Carrier> parsedCarriers = new LinkedHashMap<>();
    private Carrier currentCarrier;

    private List<BaseStorageInitorInFile.ParsedTransportation> transportations = new ArrayList<>();
    private Transportation currentTransportation;

    private Deque<String> tagStack = new ArrayDeque<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        resetStringBuilder();
        tagStack.add(qName);

        switch (stackAsStringPath()) {
            case CargoPaths.CARGO: {
                currentCargo = getCargo(attributes);
                currentCargo.setId(IdGenerator.generateId());
                currentCargo.setCargoType(CargoType.valueOf(attributes.getValue("type")));
                parsedCargos.put(attributes.getValue("id"), currentCargo);
                break;
            }

            case CarrierPaths.CARRIER: {
                currentCarrier = new Carrier();
                currentCarrier.setId(IdGenerator.generateId());
                parsedCarriers.put(attributes.getValue("id"), currentCarrier);
                break;
            }

            case TransportationPaths.TRANSPORTATION: {
                BaseStorageInitorInFile.ParsedTransportation parsedTransportation = getParsedTransportation(attributes);
                currentTransportation = parsedTransportation.getTransportation();
                currentTransportation.setId(IdGenerator.generateId());
                transportations.add(parsedTransportation);
                break;
            }
        }
    }

    private BasicCargo getCargo(Attributes attributes) {
        CargoType type = CargoType.valueOf(attributes.getValue("type"));
        switch (type) {
            case PERISHABLE:
                return new PerishableCargo();
            case OUTFIT:
                return new OutfitCargo();
            default: {
                throw new RuntimeException("Unknown cargo type '" + type + "'");
            }
        }
    }

    private BaseStorageInitorInFile.ParsedTransportation getParsedTransportation(Attributes attributes) {
        String cargoRef = attributes.getValue("cargoref");
        String carrierRef = attributes.getValue("carrierref");

        BaseStorageInitorInFile.ParsedTransportation result = new BaseStorageInitorInFile.ParsedTransportation();
        result.setCargoRef(cargoRef);
        result.setCarrierRef(carrierRef);
        result.setTransportation(new Transportation());

        return result;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        content.append(value.replaceAll("\\n", ""));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            String dataAsStr = this.content.toString();
            String path = stackAsStringPath();

            if (CargoPaths.isCargoPath(path)) {
                fillCargoWithData(currentCargo, path, dataAsStr);
            } else if (CarrierPaths.isCarrierPath(path)) {
                fillCarrierWithData(currentCarrier, path, dataAsStr);
            } else if (TransportationPaths.isTransportationPath(path)) {
                fillTransportationWithData(currentTransportation, path, dataAsStr);
            }
        } catch (Exception e) {
            throw new SAXException(e);
        }

        tagStack.removeLast();
    }

    private void fillCargoWithData(BasicCargo cargo, String tagPath, String content) throws ParseException {
        if (cargo.getClass().equals(OutfitCargo.class)) {
            fillClotherCargoWithData((OutfitCargo) cargo, tagPath, content);
        } else if (cargo.getClass().equals(PerishableCargo.class)) {
            fillFoodCargoWithData((PerishableCargo) cargo, tagPath, content);
        }

        switch (tagPath) {
            case CargoPaths.NAME: {
                cargo.setName(content);
                break;
            }
            case CargoPaths.WEIGHT: {
                cargo.setWeight(Integer.parseInt(content));
                break;
            }
        }
    }

    private void fillFoodCargoWithData(PerishableCargo foodCargo, String tagPath, String content) throws ParseException {
        switch (tagPath) {
            case CargoPaths.EXPIRATION_DATE: {
                foodCargo.setDateOfExpire(LocalDate.parse(content));
                break;
            }
            case CargoPaths.STORE_TEMPERATURE: {
                foodCargo.setStoreTemperature(Integer.parseInt(content));
                break;
            }
        }
    }

    private void fillClotherCargoWithData(OutfitCargo clothersCargo, String tagPath, String content) {
        switch (tagPath) {
            case CargoPaths.SIZE: {
                clothersCargo.setSize(Integer.valueOf(content));
                break;
            }

            case CargoPaths.GENDER: {
                clothersCargo.setGender(OutfitCargo.Gender.valueOf(content));
                break;
            }
        }
    }

    private void fillCarrierWithData(Carrier carrier, String tagPath, String content) {
        switch (tagPath) {
            case CarrierPaths.NAME: {
                carrier.setName(content);
                break;
            }
            case CarrierPaths.ADDRESS: {
                carrier.setAddress(content);
                break;
            }
            case CarrierPaths.TYPE: {
                carrier.setCarrierType(CarrierType.valueOf(content));
                break;
            }
        }
    }

    private void fillTransportationWithData(Transportation transportation, String tagPath, String content) throws ParseException {
        switch (tagPath) {
            case TransportationPaths.BILLTO: {
                transportation.setBillTo(content);
                break;
            }
            case TransportationPaths.DESCRIPTION: {
                transportation.setDescription(content);
                break;
            }
            case TransportationPaths.TRANSPORTATION_BEGIN_DATE: {
                transportation.setDate(LocalDateTime.parse(content));
                break;
            }
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }

    public Map<String, BasicCargo> getParsedCargos() {
        return parsedCargos;
    }

    public Map<String, Carrier> getParsedCarriers() {
        return parsedCarriers;
    }

    public List<BaseStorageInitorInFile.ParsedTransportation> getParsedTransportations() {
        return transportations;
    }

    private void resetStringBuilder() {
        content.setLength(0);
    }

    private String stackAsStringPath() {
        StringBuilder fullPath = new StringBuilder();

        Iterator<String> iter = tagStack.iterator();
        while (iter.hasNext()) {
            String tag = iter.next();
            fullPath.append(tag);

            if (iter.hasNext()) {
                fullPath.append("/");
            }
        }

        return fullPath.toString();
    }
}
