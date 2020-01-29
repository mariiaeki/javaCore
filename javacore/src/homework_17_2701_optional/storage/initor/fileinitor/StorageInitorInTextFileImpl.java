package homework_17_2701_optional.storage.initor.fileinitor;

import homework_17_2701_optional.cargo.domain.BasicCargo;
import homework_17_2701_optional.cargo.domain.CargoType;
import homework_17_2701_optional.cargo.domain.OutfitCargo;
import homework_17_2701_optional.cargo.domain.PerishableCargo;
import homework_17_2701_optional.carrier.domain.Carrier;
import homework_17_2701_optional.carrier.domain.CarrierType;
import homework_17_2701_optional.common.company_exceptions.checked.InitStorageException;
import homework_17_2701_optional.common.util.FileUtils;
import homework_17_2701_optional.common.util.JavaUtilDateUtils;
import homework_17_2701_optional.storage.IdGenerator;
import homework_17_2701_optional.transportation.domain.Transportation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StorageInitorInTextFileImpl extends BaseStorageInitorInFile {
    private static final String FILE = "/init-data.txt";
    private static final String CARGO_SECTION_LABEL_IN_FILE = "--Cargo section--";
    private static final String CARRIER_SECTION_LABEL_IN_FILE = "--Carrier section--";
    private static final String TRANSPORTATION_SECTION_LABEL_IN_FILE = "--Transportation section--";

    private static Map<String, BasicCargo> cargoMap;
    private static Map<String, Carrier> carrierMap;
    private static List<ParsedTransportation> transportations;


    @Override
    public void storageInitor() throws InitStorageException, IOException {
        final File file = FileUtils.createFileFromResource("init-data", "init-data-file", FILE);
        ;
        try {

            Thread thread_cargo = new Thread(() -> {
                try {
                    cargoMap = parseCargosFromFile(file);
                    persistCargos(cargoMap.values());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

            Thread thread_carrier = new Thread(() -> {
                try {
                    carrierMap = parseCarriersFromFile(file);
                    persistCarriers(carrierMap.values());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread thread_trs = new Thread(() -> {
                try {
                    transportations = parseTransportationsDataFromFile(file);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
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

//    private static class ParsedTransportation {
//
//        private String cargoRef;
//        private String carrierRef;
//        private Transportation transportation;
//    }


    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("init-data", "init-data-file", FILE);
    }

    private Map<String, BasicCargo> parseCargosFromFile(File file) throws IOException, ParseException {
        Map<String, BasicCargo> cargos = new LinkedHashMap<>();
        List<String> fileData = readSectionInFile(file, CARGO_SECTION_LABEL_IN_FILE);

        for (String cargoStr : fileData) {
            SimpleEntry<String, BasicCargo> cargoData = parseCargoFromString(cargoStr);
            if (cargoData != null) {
                cargos.put(cargoData.getKey(), cargoData.getValue());
            }
        }

        return cargos;
    }

    private SimpleEntry<String, BasicCargo> parseCargoFromString(String cargoAsStr) throws ParseException {
        String[] cargoData = cargoAsStr.split("\\|");

        if (cargoData.length > 0) {
            int index = 0;
            String id = cargoData[index++].trim();
            CargoType cargoType = CargoType.valueOf(cargoData[index++].trim());
            String name = cargoData[index++].trim();
            int weight = Integer.parseInt(cargoData[index++].trim());

            BasicCargo cargo;
            if (CargoType.OUTFIT.equals(cargoType)) {
                OutfitCargo outfitCargo = new OutfitCargo();
                outfitCargo.setGender(OutfitCargo.Gender.valueOf(cargoData[index++].trim()));
                outfitCargo.setSize(Integer.parseInt(cargoData[index].trim()));
                cargo = outfitCargo;
            } else {
                PerishableCargo perishableCargo = new PerishableCargo();
                perishableCargo.setDateOfExpire(JavaUtilDateUtils.valueOf(cargoData[index++].trim()));
                perishableCargo.setStoreTemperature(Integer.parseInt(cargoData[index].trim()));
                cargo = perishableCargo;
            }

            cargo.setId(IdGenerator.generateId());
            cargo.setName(name);
            cargo.setWeight(weight);
            cargo.setCargoType(cargoType);

            return new SimpleEntry<>(id, cargo);
        }

        return null;
    }

    private Map<String, Carrier> parseCarriersFromFile(File file) throws IOException {
        Map<String, Carrier> carriers = new LinkedHashMap<>();
        List<String> fileData = readSectionInFile(file, CARRIER_SECTION_LABEL_IN_FILE);

        for (String carriersStr : fileData) {
            SimpleEntry<String, Carrier> carrierData = parseCarrierFromString(carriersStr);
            if (carrierData != null) {
                carriers.put(carrierData.getKey(), carrierData.getValue());
            }
        }

        return carriers;
    }


    private SimpleEntry<String, Carrier> parseCarrierFromString(String carrierAsStr) {
        String[] data = carrierAsStr.split("\\|");

        if (data.length > 0) {
            int index = 0;
            String id = data[index++].trim();
            Carrier carrier = new Carrier();
            carrier.setId(IdGenerator.generateId());
            carrier.setName(data[index++].trim());
            carrier.setAddress(data[index++].trim());
            carrier.setCarrierType(CarrierType.valueOf(data[index].trim()));

            return new SimpleEntry<>(id, carrier);
        }

        return null;
    }

    private List<String> readSectionInFile(File file, String sectionLabel)
            throws IOException {

        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean sectionHasStarted = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (sectionHasStarted) {
                    if (line.isEmpty()) {
                        break;
                    }
                    result.add(line);
                }

                if (!sectionHasStarted && sectionLabel.equals(line)) {
                    sectionHasStarted = true;
                }
            }
        }

        return result;
    }

    private List<ParsedTransportation> parseTransportationsDataFromFile(File file)
            throws IOException, ParseException {
        List<ParsedTransportation> result = new ArrayList<>();
        List<String> transportationSection = readSectionInFile(file,
                TRANSPORTATION_SECTION_LABEL_IN_FILE);

        for (String transportationDataStr : transportationSection) {
            result.add(parseTransportationDataFromString(transportationDataStr));
        }
        return result;
    }

    private ParsedTransportation parseTransportationDataFromString(String transportationDataStr)
            throws ParseException {
        String data[] = transportationDataStr.split("\\|");

        ParsedTransportation result = null;
        if (data.length > 0) {
            result = new ParsedTransportation();
            int index = 0;
            result.setCargoRef(data[index++].trim());
            result.setCarrierRef(data[index++].trim());

            Transportation transportation = new Transportation();
            transportation.setId(IdGenerator.generateId());
            transportation.setDescription(data[index++].trim());
            transportation.setBillTo(data[index++].trim());
            transportation.setDate(JavaUtilDateUtils.valueOf(data[index].trim()));
            result.setTransportation(transportation);
        }

        return result;
    }


}
