package homework_10_2312.carrier.service;

import homework_10_2312.carrier.domain.Carrier;
import homework_10_2312.carrier.exceptions.CarrierDeleteConstraintViolationException;
import homework_10_2312.carrier.repo.CarrierRepo;
import homework_10_2312.transportation.domain.Transportation;

import java.util.List;

public class CarrierServiceImpl implements CarrierService {
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
}
