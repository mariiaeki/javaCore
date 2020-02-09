package homework_17_18_19_optional_date_dbrepo.storage.initor;

import homework_17_18_19_optional_date_dbrepo.application.serviceholder.ServiceHolder;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.OutfitCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.PerishableCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.service.CargoService;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;
import homework_17_18_19_optional_date_dbrepo.carrier.service.CarrierService;
import homework_17_18_19_optional_date_dbrepo.transportation.domain.Transportation;
import homework_17_18_19_optional_date_dbrepo.transportation.service.TrsService;
import homework_5_1112.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static homework_8_and_9.common.util.CollectionUtils.isNotEmpty;

public class StorageInitorInMemoryImpl implements StorageInitor {
    private static final int TOTAL_ENTITIES_IN_GROUP = 6;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TrsService transportationService;

    public StorageInitorInMemoryImpl() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void storageInitor() {
        initCargos();
        initCarriers();
        initTransportations();
        appendTransportationsToCargos();
        appendTransportationsToCarriers();

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
        cargo.setDateOfExpire(LocalDate.of(2020,10,12));
        cargo.setWeight(2 + index);
        cargo.setStoreTemperature(index);
        cargo.setName("FoodCargo_Name_" + index);

        return cargo;
    }

    private PerishableCargo createPerishableCargo(int index, String name) {
        PerishableCargo cargo = new PerishableCargo();
        cargo.setId(IdGenerator.generateId());
        cargo.setDateOfExpire(LocalDate.of(2021,01,02));
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
        transportation.setCargo(cargoService.getById(cargoId).get());
        transportation.setCarrier(carrierService.getById(carrierId).get());
        transportation.setDescription("Transportation");

        return transportation;
    }

    private void appendTransportationsToCargos() {
        List<BasicCargo> cargos = cargoService.getAll();
        List<Transportation> transportations = transportationService.getAll();

        if (isNotEmpty(cargos) && isNotEmpty(transportations)) {
            for (BasicCargo cargo : cargos) {
                appendTransportationsToCargo(cargo, transportations);
            }
        }
    }

    private void appendTransportationsToCarriers() {
        List<Carrier> carriers = carrierService.getAll();
        List<Transportation> transportations = transportationService.getAll();

        if (isNotEmpty(carriers) && isNotEmpty(transportations)) {
            for (Carrier carrier: carriers){
                appendTransportationsToCarrier(carrier, transportations);
            }
        }
    }

    private void appendTransportationsToCargo(BasicCargo cargo,
                                              List<Transportation> transportations) {

        List<Transportation> cargoTransportations = cargo.getTransportations();
        if (cargoTransportations == null) {
            cargoTransportations = new ArrayList<>();
        }

        for (Transportation transportation : transportations) {
            if (transportation.getCargo() != null && transportation.getCargo().getId()
                    .equals(cargo.getId())) {
                cargoTransportations.add(transportation);
            }
        }

        cargo.setTransportations(transportations);
    }

    private void appendTransportationsToCarrier(Carrier carrier, List<Transportation> transportations){
        List<Transportation> carrierTransportations = carrier.getTransportations();

        if (carrierTransportations == null){
            carrierTransportations = new ArrayList<>();
        }

        for (Transportation trs: transportations){
            if (trs.getCargo() != null && trs.getCargo().getId().equals(carrier.getId())) {
                carrierTransportations.add(trs);
            }
        }
        carrier.setTransportations(transportations);
    }
}
