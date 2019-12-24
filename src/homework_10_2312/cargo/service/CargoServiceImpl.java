package homework_10_2312.cargo.service;

import homework_10_2312.common.comparator.EntitySortConditions;
import homework_10_2312.cargo.domain.BasicCargo;
import homework_10_2312.cargo.exceptions.CargoDeleteConstraintViolationException;
import homework_10_2312.cargo.repo.CargoRepo;
import homework_10_2312.transportation.domain.Transportation;

import java.util.List;

public class CargoServiceImpl implements CargoService {
    private final CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void add(BasicCargo cargo) {
        if (cargo != null) {
            cargoRepo.add(cargo);
        }
    }

    @Override
    public BasicCargo getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }
        return null;
    }

    @Override
    public BasicCargo[] getByName(String name) {
        if (name != null) {
            return cargoRepo.getByName(name);
        }
        return new BasicCargo[0];
    }

    @Override
    public List<BasicCargo> getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public void printAll() {
        List<BasicCargo> allCargos = cargoRepo.getAll();

        for (BasicCargo cargo : allCargos) {
            System.out.println(cargo);
        }

    }

    @Override
    public void update(BasicCargo cargo) {
        if (cargo != null) {
            cargoRepo.update(cargo);
        }
    }

    @Override
    public List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions) {
        return cargoRepo.getSortedCargos(searchConditions);
    }


    @Override
    public boolean deleteById(Long id) {
        BasicCargo cargo = this.getByIdFetchingTransportations(id);

        if (cargo != null) {
            List<Transportation> transportations = cargo.getTransportations();
            boolean hasTransportations = transportations != null && transportations.size() > 0;
            if (hasTransportations) {
                throw new CargoDeleteConstraintViolationException(id);
            }

            return cargoRepo.deleteById(id);
        } else {
            return false;
        }
    }

    @Override
    public BasicCargo getByIdFetchingTransportations(Long id) {
        if (id != null){
            return cargoRepo.getByIdFetchingTransportations(id);
        }
        return null;
    }
}
