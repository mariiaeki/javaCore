package homework_15_2001_concurrency.application;

import homework_15_2001_concurrency.application.serviceholder.ServiceHolder;
import homework_15_2001_concurrency.application.serviceholder.StorageType;
import homework_15_2001_concurrency.cargo.domain.BasicCargo;
import homework_15_2001_concurrency.cargo.domain.CargoField;
import homework_15_2001_concurrency.cargo.service.CargoService;
import homework_15_2001_concurrency.carrier.service.CarrierService;
import homework_15_2001_concurrency.common.company_exceptions.checked.InitStorageException;
import homework_15_2001_concurrency.common.company_exceptions.checked.ReportExceptions;
import homework_15_2001_concurrency.common.comparator.EntitySortConditions;
import homework_15_2001_concurrency.common.search.OrderType;
import homework_15_2001_concurrency.common.util.ArrayUtils;
import homework_15_2001_concurrency.reporting.ReportDefaultService;
import homework_15_2001_concurrency.reporting.ReportService;
import homework_15_2001_concurrency.storage.initor.InitStorageType;
import homework_15_2001_concurrency.storage.initor.StorageInitor;
import homework_15_2001_concurrency.transportation.service.TrsService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

import static homework_15_2001_concurrency.cargo.domain.CargoField.NAME;
import static homework_15_2001_concurrency.cargo.domain.CargoField.WEIGHT;
import static homework_15_2001_concurrency.common.search.OrderType.ASC;
import static homework_15_2001_concurrency.common.search.OrderType.DESC;
import static homework_15_2001_concurrency.common.util.CollectionUtils.printCollection;
import static homework_15_2001_concurrency.storage.initor.StorageInitorFactory.getStorageInitor;
import static java.util.Collections.singletonList;

public class Application {
    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TrsService transportationService;

    public static void main(String[] args) {
        try {
            ServiceHolder.initServiceHolder(StorageType.COLLECTION);
            cargoService = ServiceHolder.getInstance().getCargoService();
            carrierService = ServiceHolder.getInstance().getCarrierService();
            transportationService = ServiceHolder.getInstance().getTransportationService();

            StorageInitor storageInitor = getStorageInitor(InitStorageType.TEXT_FILE);
            storageInitor.storageInitor();

            //Инициализация через 2 документа, синк по присваиванию id.
            //Один раз из 8 делает неправильно (null при добавлении)
            //initStorage();

            printStorageData();

            //demoDeleteById();

            demoSortOperations();

            //doSearchOperations();

            //demoReportService();

        } catch (InitStorageException e) {
            e.getCause().printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initStorage() throws InterruptedException {
        Thread thread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                StorageInitor storageInitor = getStorageInitor(InitStorageType.XML_FILE);
                try {
                    storageInitor.storageInitor();
                } catch (InitStorageException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                StorageInitor storageInitor = getStorageInitor(InitStorageType.TEXT_FILE);
                try {
                    storageInitor.storageInitor();
                } catch (InitStorageException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();
    }


    private static void demoDeleteById() {
        System.out.println("------Demo  exceptions------------");
        Long firstCargo = cargoService.getAll().get(0).getId();
        BasicCargo cargo = cargoService.getByIdFetchingTransportations(firstCargo);
        System.out.println("Try to delete cargo");
        System.out.println("Cargo details:");
        System.out.println("id: " + cargo.getId());
        System.out.println("name: " + cargo.getName());
        System.out.println("total transportations: " + (cargo.getTransportations() != null ? cargo
                .getTransportations().size() : 0));
        System.out.println();
        try {
            cargoService.deleteById(cargo.getId());
        } catch (Exception e) {
            System.out.println("OOPS, something went wrong!");
            System.out.println(e.getMessage());
        }
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
        EntitySortConditions<CargoField> cargoSearchCondition = new EntitySortConditions();
        cargoSearchCondition.setOrderType(orderType);
        cargoSearchCondition.setSortFields(new LinkedHashSet<>(sortFields));

        System.out.println("Sorted Cargos: ");
        printCollection(cargoService.getSortedCargos(cargoSearchCondition));
        printSeparator();


    }

    private static void demoSortOperations() {
        demoCargoSorting(singletonList(NAME), ASC);
        demoCargoSorting(singletonList(NAME), DESC);

        demoCargoSorting(singletonList(WEIGHT), ASC);
        demoCargoSorting(singletonList(WEIGHT), DESC);

        demoCargoSorting(Arrays.asList(NAME, WEIGHT), ASC);
        demoCargoSorting(Arrays.asList(NAME, WEIGHT), DESC);
    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    private static void demoReportService() throws ReportExceptions {
        System.out.println("----------Demo report service ---------------");
        ReportService reportService = new ReportDefaultService(
                cargoService, carrierService, transportationService
        );
        reportService.exportData();
    }


}
