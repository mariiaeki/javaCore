package homework_8_1812.cargo.service;

import homework_8_1812.cargo.comparator.CargoNameComparator;
import homework_8_1812.cargo.repo.CargoRepo;
import homework_8_1812.cargo.domain.BasicCargo;
import sun.misc.BASE64Decoder;

import java.util.Collections;
import java.util.Comparator;
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
    public void sortCargosByNameAndWeight() {
        if (cargoRepo != null) {
            cargoRepo.sortCargosByNameAndWeight();
        }
    }

    @Override
    public void sortCargosByName() {
        if (cargoRepo != null) {
            cargoRepo.sortCargosByName();
        }
    }

    @Override
    public void sortCargosByWeight() {
        if (cargoRepo != null) {
            cargoRepo.sortCargosByWeight();
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != 0) {
            cargoRepo.deleteById(id);
        }
    }
}
