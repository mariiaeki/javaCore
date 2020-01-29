package homework_17_2701_optional.carrier.service;

import homework_17_2701_optional.carrier.exceptions.CarrierDeleteConstraintViolationException;
import homework_17_2701_optional.carrier.domain.Carrier;
import homework_17_2701_optional.carrier.repo.CarrierRepo;
import homework_17_2701_optional.common.company_exceptions.uncheked.NoEntityException;
import homework_17_2701_optional.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class CarrierServiceImpl implements CarrierService, Serializable {
    private final CarrierRepo carrierRepo;

    public CarrierServiceImpl(CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    @Override
    public void add(Carrier carrier) {
        if (carrier != null) {
            carrierRepo.add(carrier);
        }
    }

    @Override
    public Carrier getById(Long id) {
        return carrierRepo.getById(id).orElseThrow(() ->
                new NoEntityException("There is no carrier with this id"));
    }

    @Override
    public Carrier[] getByName(String name) {
        if (name != null) {
            return carrierRepo.getByName(name);
        }
        return new Carrier[0];
    }

    @Override
    public List<Carrier> getAll() {
        return carrierRepo.getAll();
    }

    @Override
    public void printAll() {
        List<Carrier> allCarriers = carrierRepo.getAll();

        for (Carrier carrier : allCarriers) {
            System.out.println(carrier);
        }

    }

    @Override
    public void update(Carrier carrier) {
        if (carrier != null) {
            carrierRepo.update(carrier);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null) {
            Carrier carrier =
                    this.getByIdFetchingTransportations(id).orElseThrow(()
                            -> new NoEntityException("There is no carrier with this id"));

            List<Transportation> transportations = carrier.getTransportations();
            if (transportations != null && transportations.size() > 0) {
                throw new CarrierDeleteConstraintViolationException(id);
            }
            return carrierRepo.deleteById(id);
        }else {
            return false;
        }
    }

    @Override
    public Optional<Carrier> getByIdFetchingTransportations(Long id) {
        return carrierRepo.getByIdFetchingTransportations(id);

    }

    @Override
    public String toString() {
        return "CarrierServiceImpl{" +
                "carrierRepo=" + carrierRepo +
                '}';
    }
}
