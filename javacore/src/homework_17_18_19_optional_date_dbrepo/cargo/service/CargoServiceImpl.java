package homework_17_18_19_optional_date_dbrepo.cargo.service;

import homework_17_18_19_optional_date_dbrepo.application.serviceholder.ServiceHolder;
import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.cargo.exceptions.CargoDeleteConstraintViolationException;
import homework_17_18_19_optional_date_dbrepo.cargo.repo.CargoRepo;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;
import homework_17_18_19_optional_date_dbrepo.common.company_exceptions.uncheked.NoEntityException;
import homework_17_18_19_optional_date_dbrepo.common.comparator.EntitySortConditions;
import homework_17_18_19_optional_date_dbrepo.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.Collections;
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
    public void addAll(List<BasicCargo> cargos) {
        if (cargos != null) {
            cargoRepo.addAll(cargos);
        }
    }

    @Override
    public Optional<BasicCargo> getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }
        return Optional.empty();

    }

    @Override
    public List<BasicCargo> getByName(String name) {
        if (name != null) {
            return cargoRepo.getByName(name);
        }
        return Collections.EMPTY_LIST;
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
    public Optional<BasicCargo> getByIdFetchingTransportations(Long id) {
        if (id != null) {
            return cargoRepo.getByIdFetchingTransportations(id);
        }
        return Optional.empty();

    }

    @Override
    public void addCargosAndCarriers(List<BasicCargo> cargos, List<Carrier> carriers) {
        cargoRepo.addAll(cargos);
        ServiceHolder.getInstance().getCarrierService().addAll(carriers);
        // (?).(?)

    }

    @Override
    public String toString() {
        return "CargoServiceImpl{" +
                "cargoRepo=" + cargoRepo +
                '}';
    }
}
