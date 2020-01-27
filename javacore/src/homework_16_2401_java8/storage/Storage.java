package homework_16_2401_java8.storage;

import homework_16_2401_java8.cargo.domain.BasicCargo;
import homework_16_2401_java8.carrier.domain.Carrier;
import homework_16_2401_java8.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Storage implements Serializable {
    public static final int STORAGE_CAPACITY = 10;

    public static BasicCargo[] cargoStorage = new BasicCargo[STORAGE_CAPACITY];
    public static Carrier[] carrierStorage = new Carrier[STORAGE_CAPACITY];
    public static Transportation[] transportationsStorage = new Transportation[STORAGE_CAPACITY];

    public static int kCargo = 0;
    public static int kCarrier = 0;
    public static int kTrnsp = 0;

    public static List<BasicCargo> cargoStorageList = new ArrayList<>();
    public static List<Carrier> carrierStorageList = new ArrayList<>();
    public static List<Transportation> trsStorageList = new ArrayList<>();

//    public void deleteCargoById(long index) {
//        BasicCargo[] newCargoStorage = new BasicCargo[kCargo - 1];
//        for (int i = 0; i < newCargoStorage.length; i++) {
//            if (i < index) {
//                newCargoStorage[i] = cargoStorage[i];
//            } else if (i > index) {
//                newCargoStorage[i] = cargoStorage[i + 1];
//            }
//        }
//    }

    public BasicCargo[] getAllCargos() {
        return cargoStorage;
    }

    public Carrier[] getAllCarriers() {
        return carrierStorage;
    }

    public Transportation[] getAllTransportations() {
        return transportationsStorage;
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


}
