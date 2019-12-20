package homework_8_1812.application;

import homework_8_1812.application.serviceholder.ServiceHolder;
import homework_8_1812.application.serviceholder.StorageType;
import homework_8_1812.cargo.service.CargoService;
import homework_8_1812.carrier.service.CarrierService;
import homework_8_1812.common.util.ArrayUtils;
import homework_8_1812.common.util.CollectionUtils;
import homework_8_1812.storage.initor.StorageInitor;
import homework_8_1812.storage.initor.StorageInitorImpl;
import homework_8_1812.transportation.service.TrsService;

import java.util.ArrayList;

public class Application {
    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TrsService transportationService;

    public static void main(String[] args) {
        ServiceHolder.initServiceHolder(StorageType.COLLECTION);
        cargoService = ServiceHolder.getInstance().getCargoService();
        carrierService = ServiceHolder.getInstance().getCarrierService();
        transportationService = ServiceHolder.getInstance().getTransportationService();

        StorageInitor storageInitor = new StorageInitorImpl();
        storageInitor.storageInitor();

        printStorageData();
        doCargoSort();
        doSearchOperations();
    }

    private static void printStorageData() {
        System.out.println("ALL CARGOS");
        cargoService.printAll();
        printSeparator();

        System.out.println("ALL CARRIERS");
        carrierService.printAll();
        printSeparator();

        System.out.println("ALL TRANSPOORTATIONS");
        transportationService.printAll();
        printSeparator();
    }

    private static void doSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        System.out.println(cargoService.getById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 8");
        System.out.println(carrierService.getById(8L));
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'Clothers_Name_1'");
        ArrayUtils.printArray(cargoService.getByName("Clothers_Name_1"));
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name_2'");
        ArrayUtils.printArray(carrierService.getByName("Carrier_Name_2"));
    }

    private static void doCargoSort(){
        cargoService.sortCargosByNameAndWeight();
        //cargoService.sortCargosByName();
        //cargoService.sortCargosByWeight();

        System.out.println("ALL SORTED CARGOS");
        cargoService.printAll();
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }


}
