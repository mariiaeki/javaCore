package homework_15_2001_concurrency.carrier.service;

import homework_15_2001_concurrency.carrier.domain.Carrier;
import homework_15_2001_concurrency.carrier.exceptions.CarrierDeleteConstraintViolationException;
import homework_15_2001_concurrency.carrier.repo.CarrierRepo;
import homework_15_2001_concurrency.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.List;

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
        if (id != null) {
            return carrierRepo.getById(id);
        }
        return null;
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

        for( Carrier carrier: allCarriers){
            System.out.println(carrier);
        }

    }

    @Override
    public void update(Carrier carrier) {
        if (carrier != null){
            carrierRepo.update(carrier);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Carrier carrier = this.getByIdFetchingTransportations(id);

        if (carrier != null) {
            List<Transportation> transportations = carrier.getTransportations();
            boolean hasTransportations = transportations != null && transportations.size() > 0;
            if (hasTransportations) {
                throw new CarrierDeleteConstraintViolationException(id);
            }

            return carrierRepo.deleteById(id);
        } else {
            return false;
        }
    }

    @Override
    public Carrier getByIdFetchingTransportations(Long id) {
        if (id != null){
            return carrierRepo.getByIdFetchingTransportations(id);
        }
        return null;
    }

    @Override
    public String toString() {
        return "CarrierServiceImpl{" +
                "carrierRepo=" + carrierRepo +
                '}';
    }
}
