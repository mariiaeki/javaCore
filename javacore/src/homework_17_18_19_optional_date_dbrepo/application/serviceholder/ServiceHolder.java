package homework_17_18_19_optional_date_dbrepo.application.serviceholder;

import homework_17_18_19_optional_date_dbrepo.cargo.repo.CargoDatabaseRepoImpl;
import homework_17_18_19_optional_date_dbrepo.cargo.repo.CargoDefaultRepoImpl;
import homework_17_18_19_optional_date_dbrepo.carrier.repo.CarrierCollectionRepoImpl;
import homework_17_18_19_optional_date_dbrepo.cargo.repo.CargoCollectionRepoImpl;
import homework_17_18_19_optional_date_dbrepo.cargo.service.CargoService;
import homework_17_18_19_optional_date_dbrepo.cargo.service.CargoServiceImpl;
import homework_17_18_19_optional_date_dbrepo.carrier.repo.CarrierDatabaseRepoImpl;
import homework_17_18_19_optional_date_dbrepo.carrier.repo.CarrierDefaultRepoImpl;
import homework_17_18_19_optional_date_dbrepo.carrier.service.CarrierService;
import homework_17_18_19_optional_date_dbrepo.carrier.service.CarrierServiceImpl;
import homework_17_18_19_optional_date_dbrepo.transportation.repo.TrsCollectionRepoImpl;
import homework_17_18_19_optional_date_dbrepo.transportation.repo.TrsDatabaseRepoImpl;
import homework_17_18_19_optional_date_dbrepo.transportation.repo.TrsDefaultRepoImpl;
import homework_17_18_19_optional_date_dbrepo.transportation.service.TrsService;
import homework_17_18_19_optional_date_dbrepo.transportation.service.TrsServiceImpl;

public class ServiceHolder {
    private static ServiceHolder instance = null;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TrsService trsService;

    private ServiceHolder(StorageType storageType) {
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

            case DATABASE: {
                return new SimpleServiceHolder(
                        new CarrierServiceImpl(new CarrierDatabaseRepoImpl()),
                        new CargoServiceImpl(new CargoDatabaseRepoImpl()),
                        new TrsServiceImpl(new TrsDatabaseRepoImpl()));
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

    public CargoService getCargoService() {
        return cargoService;
    }

    public TrsService getTransportationService() {
        return trsService;
    }


}
