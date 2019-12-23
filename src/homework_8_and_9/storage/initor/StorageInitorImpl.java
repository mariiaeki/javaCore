package homework_8_and_9.storage.initor;

import homework_5_1112.IdGenerator;
import homework_8_and_9.application.serviceholder.ServiceHolder;
import homework_8_and_9.cargo.domain.OutfitCargo;
import homework_8_and_9.cargo.domain.PerishableCargo;
import homework_8_and_9.cargo.service.CargoService;
import homework_8_and_9.carrier.domain.Carrier;
import homework_8_and_9.carrier.service.CarrierService;
import homework_8_and_9.transportation.domain.Transportation;
import homework_8_and_9.transportation.service.TrsService;

import java.util.Date;

public class StorageInitorImpl implements StorageInitor {
    private static final int TOTAL_ENTITIES_IN_GROUP = 6;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TrsService transportationService;

    public StorageInitorImpl() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void storageInitor() {
        initCargos();
        initCarriers();
        initTransportations();
    }

    private void initCargos() {
        cargoService.add(createOutfitCargo(120, "Clothers_Name_1"));
        cargoService.add(createPerishableCargo(15, "FoodCargo_Name_0"));

        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.add(createOutfitCargo(i));
        }

        cargoService.add(createPerishableCargo(13, "FoodCargo_Name_1"));
        cargoService.add(createOutfitCargo(80, "Clothers_Name_2"));

        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.add(createPerishableCargo(i));
        }

    }

    private OutfitCargo createOutfitCargo(int index) {
        OutfitCargo cargo = new OutfitCargo();
        cargo.setId(IdGenerator.generateId());
        cargo.setSize(3 + index);
        cargo.setWeight(4 + index);
        cargo.setName("Clothers_Name_" + index);

        return cargo;
    }

    private OutfitCargo createOutfitCargo(int index, String name) {
        OutfitCargo cargo = new OutfitCargo();
        cargo.setId(IdGenerator.generateId());
        cargo.setSize(3 + index);
        cargo.setWeight(index);
        cargo.setName(name);

        return cargo;
    }


    private PerishableCargo createPerishableCargo(int index) {
        PerishableCargo cargo = new PerishableCargo();
        cargo.setId(IdGenerator.generateId());
        cargo.setDateOfExpire(new Date());
        cargo.setWeight(2 + index);
        cargo.setStoreTemperature(index);
        cargo.setName("FoodCargo_Name_" + index);

        return cargo;
    }

    private PerishableCargo createPerishableCargo(int index, String name) {
        PerishableCargo cargo = new PerishableCargo();
        cargo.setId(IdGenerator.generateId());
        cargo.setDateOfExpire(new Date());
        cargo.setWeight(index);
        cargo.setStoreTemperature(index);
        cargo.setName(name);

        return cargo;
    }

    private void initCarriers() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Carrier carrier = createCarrier(i);
            carrierService.add(carrier);
        }
    }

    private Carrier createCarrier(int index) {
        Carrier carrier = new Carrier();
        carrier.setId(IdGenerator.generateId());
        carrier.setName("Carrier_Name_" + index);
        carrier.setAddress("Address_" + index);
        return carrier;
    }

    private void initTransportations() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Transportation transportation = createTransportation(i + 1, i + 1 + TOTAL_ENTITIES_IN_GROUP);
            transportationService.add(transportation);
        }
    }

    private Transportation createTransportation(long cargoId, long carrierId) {
        Transportation transportation = new Transportation();
        transportation.setId(IdGenerator.generateId());
        transportation.setCargo(cargoService.getById(cargoId));
        transportation.setCarrier(carrierService.getById(carrierId));
        transportation.setDescription("Transportation");

        return transportation;
    }
}
