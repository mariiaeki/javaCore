package homework_5_1112;

import homework_5_1112.cargo.BasicCargo;
import homework_5_1112.carrier.Carrier;
import homework_5_1112.transportation.Transportation;

public class Storage {
    private static final int STORAGE_CAPACITY = 10;

    private static BasicCargo[] cargoStorage = new BasicCargo[STORAGE_CAPACITY];
    private static Carrier[] carrierStorage = new Carrier[STORAGE_CAPACITY];
    private static Transportation[] transportationsStorage = new Transportation[STORAGE_CAPACITY];

    private int kCargo = 0;
    private int kCarrier = 0;
    private int kTrnsp = 0;

    public void addCargo(BasicCargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargoStorage[kCargo] = cargo;
        kCargo++;

        if (kCargo % (STORAGE_CAPACITY - 1) == 0) {
            BasicCargo[] newCargoStorage = new BasicCargo[kCargo + STORAGE_CAPACITY];
            copyCargoArray(cargoStorage, newCargoStorage);
            cargoStorage = newCargoStorage;
        }
    }

    private static void copyCargoArray(BasicCargo[] src, BasicCargo[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    public void addCarrier(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carrierStorage[kCarrier] = carrier;
        kCarrier++;

        if (kCarrier % (STORAGE_CAPACITY - 1) == 0) {
            Carrier[] newCarrierStorage = new Carrier[kCarrier + STORAGE_CAPACITY];
            copyCarrierArray(carrierStorage, newCarrierStorage);
            carrierStorage = newCarrierStorage;
        }
    }

    private static void copyCarrierArray(Carrier[] src, Carrier[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    public void addTransportation(Transportation trs) {
        trs.setId(IdGenerator.generateId());
        transportationsStorage[kTrnsp] = trs;
        kTrnsp++;

        if (kTrnsp % (STORAGE_CAPACITY - 1) == 0) {
            Transportation[] newTrsStorage = new Transportation[kTrnsp + STORAGE_CAPACITY];
            copyTrsArray(transportationsStorage, newTrsStorage);
            transportationsStorage = newTrsStorage;
        }
    }

    private static void copyTrsArray(Transportation[] src, Transportation[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    public void printAllCargo() {
        for (int i = 0; i < kCargo; i++) {
            System.out.println(cargoStorage[i]);
        }
    }

    public void printAllCarrier() {
        for (int i = 0; i < kCarrier; i++) {
            System.out.println(carrierStorage[i]);
        }
    }

    public void printAllTransportation() {
        for (int i = 0; i < kTrnsp; i++) {
            System.out.println(transportationsStorage[i]);
        }
    }

    public BasicCargo getCargoById(Long id) {
        for (int i = 0; i < cargoStorage.length; i++) {
            if (cargoStorage[i] != null && cargoStorage[i].getId().equals(id)) {
                return cargoStorage[i];
            }
        }
        return null;
    }

    public BasicCargo[] getCargoByName(String name) {
        int k = 1;
        BasicCargo[] cargoByName = new BasicCargo[k];

        for (BasicCargo cargo : cargoStorage) {
            if (cargo != null && cargo.getName().equals(name)) {
                if (k == 1) {
                    cargoByName[k - 1] = cargo;
                    k++;
                } else {
                    BasicCargo[] newCargoByName = new BasicCargo[k];
                    copyCargoArray(cargoByName, newCargoByName);
                    cargoByName = newCargoByName;
                    cargoByName[k - 1] = cargo;
                    k++;
                }
            }
        }
        return cargoByName;
    }

    public Carrier getCarrierById(Long id) {
        for (Carrier carrier : carrierStorage) {
            if (carrier != null && carrier.getId().equals(id)) {
                return carrier;
            }
        }
        return null;
    }

    public Carrier[] getCarrierByName(String name) {
        int k = 1;
        Carrier[] carrierByName = new Carrier[k];

        for (Carrier carrier : carrierStorage) {
            if (carrier != null && carrier.getName().equals(name)) {
                if (k == 1) {
                    carrierByName[k - 1] = carrier;
                    k++;
                } else {
                    Carrier[] newCarrierByName = new Carrier[k];
                    copyCarrierArray(carrierByName, newCarrierByName);
                    carrierByName = newCarrierByName;
                    carrierByName[k - 1] = carrier;
                    k++;
                }
            }
        }
        return carrierByName;
    }

    public Transportation getTransportationById(Long id) {
        for (Transportation trs : transportationsStorage) {
            if (trs != null && trs.getId().equals(id)) {
                return trs;
            }
        }
        return null;
    }

    public BasicCargo[] getAllCargos() {
        return cargoStorage;
    }

    public Carrier[] getAllCarriers() {
        return carrierStorage;
    }

    public Transportation[] getAllTransportations() {
        return transportationsStorage;
    }




}
