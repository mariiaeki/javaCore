package homework_8_and_9.application;

import homework_8_and_9.application.serviceholder.ServiceHolder;
import homework_8_and_9.application.serviceholder.StorageType;
import homework_8_and_9.cargo.comparator.CargoSearchConditions;
import homework_8_and_9.cargo.domain.CargoField;
import homework_8_and_9.cargo.service.CargoService;
import homework_8_and_9.carrier.service.CarrierService;
import homework_8_and_9.common.search.OrderType;
import homework_8_and_9.common.util.ArrayUtils;
import homework_8_and_9.storage.initor.StorageInitor;
import homework_8_and_9.storage.initor.StorageInitorImpl;
import homework_8_and_9.transportation.service.TrsService;

import java.util.*;

import static homework_8_and_9.cargo.domain.CargoField.NAME;
import static homework_8_and_9.cargo.domain.CargoField.WEIGHT;
import static homework_8_and_9.common.search.OrderType.ASC;
import static homework_8_and_9.common.util.CollectionUtils.printCollection;

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

        demoDeleteById(1553L);

        demoCargoSorting(Arrays.asList(NAME, WEIGHT), ASC);

        doSearchOperations();
    }

    private static void demoDeleteById(Long id){
        System.out.println("Carriers with deleted element: ");
        System.out.println(carrierService.deleteById(id));
        carrierService.printAll();
        printSeparator();

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

    private static void demoCargoSorting(Collection<CargoField> sortFields, OrderType orderType) {
        CargoSearchConditions cargoSearchCondition = new CargoSearchConditions();
        cargoSearchCondition.setOrderType(orderType);
        cargoSearchCondition.setSortFields(new LinkedHashSet<>(sortFields));

        System.out.println("Sorted Cargos: ");
        printCollection(cargoService.getSortedCargos(cargoSearchCondition));
        printSeparator();


    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }


}
