package homework_16_2401_java8.application.serviceholder;

import homework_16_2401_java8.cargo.repo.CargoCollectionRepoImpl;
import homework_16_2401_java8.cargo.repo.CargoDefaultRepoImpl;
import homework_16_2401_java8.cargo.service.CargoService;
import homework_16_2401_java8.cargo.service.CargoServiceImpl;
import homework_16_2401_java8.carrier.repo.CarrierCollectionRepoImpl;
import homework_16_2401_java8.carrier.repo.CarrierDefaultRepoImpl;
import homework_16_2401_java8.carrier.service.CarrierService;
import homework_16_2401_java8.carrier.service.CarrierServiceImpl;
import homework_16_2401_java8.transportation.repo.TrsCollectionRepoImpl;
import homework_16_2401_java8.transportation.repo.TrsDefaultRepoImpl;
import homework_16_2401_java8.transportation.service.TrsService;
import homework_16_2401_java8.transportation.service.TrsServiceImpl;

public class ServiceHolder {
    private static ServiceHolder instance = null;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TrsService trsService;

    private ServiceHolder(StorageType storageType){
        SimpleServiceHolder initedServiceHolder = getInitedServiceHolder(storageType);
        cargoService = initedServiceHolder.cargoService;
        carrierService = initedServiceHolder.carrierService;
        trsService = initedServiceHolder.trsService;

    }

    public static void initServiceHolder(StorageType storageType) {
        instance = new ServiceHolder(storageType);
    }

    public static ServiceHolder getInstance() {
        return instance;
    }


    private static class SimpleServiceHolder {

        private final CarrierService carrierService;
        private final CargoService cargoService;
        private final TrsService trsService;

        public SimpleServiceHolder(
                CarrierService carrierService,
                CargoService cargoService,
                TrsService transportationService) {
            this.carrierService = carrierService;
            this.cargoService = cargoService;
            this.trsService = transportationService;
        }
    }

    private SimpleServiceHolder getInitedServiceHolder(StorageType storageType) {
        switch (storageType) {

            case ARRAY: {
                return new SimpleServiceHolder(
                        new CarrierServiceImpl(new CarrierDefaultRepoImpl()),
                        new CargoServiceImpl(new CargoDefaultRepoImpl()),
                        new TrsServiceImpl(new TrsDefaultRepoImpl()));
            }

            default: {
                return new SimpleServiceHolder(
                        new CarrierServiceImpl(new CarrierCollectionRepoImpl()),
                        new CargoServiceImpl(new CargoCollectionRepoImpl()),
                        new TrsServiceImpl(new TrsCollectionRepoImpl()));
            }
        }
    }

        public CarrierService getCarrierService() {
            return carrierService;
        }

        public CargoService getCargoService () {
            return cargoService;
        }

        public TrsService getTransportationService () {
            return trsService;
        }


}
