package homework_17_2701_optional.cargo.service;

import homework_17_2701_optional.cargo.domain.BasicCargo;
import homework_17_2701_optional.cargo.exceptions.CargoDeleteConstraintViolationException;
import homework_17_2701_optional.cargo.repo.CargoRepo;
import homework_17_2701_optional.common.company_exceptions.uncheked.NoEntityException;
import homework_17_2701_optional.common.comparator.EntitySortConditions;
import homework_17_2701_optional.transportation.domain.Transportation;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class CargoServiceImpl implements CargoService, Serializable {
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
        return cargoRepo.getById(id).orElseThrow(() ->
                new NoEntityException("There is no cargo with this id"));

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
        if (id != null) {
            BasicCargo cargo =
                    this.getByIdFetchingTransportations(id).orElseThrow(()
                            -> new NoEntityException("There is no cargo with this id"));

            List<Transportation> transportations = cargo.getTransportations();
            if (transportations != null && transportations.size() > 0) {
                throw new CargoDeleteConstraintViolationException(id);
            }
            return cargoRepo.deleteById(id);
        } else {
            return false;
        }

    }

    @Override
    public Optional<BasicCargo> getByIdFetchingTransportations(long id) {
        return cargoRepo.getByIdFetchingTransportations(id);

    }

    @Override
    public String toString() {
        return "CargoServiceImpl{" +
                "cargoRepo=" + cargoRepo +
                '}';
    }
}
