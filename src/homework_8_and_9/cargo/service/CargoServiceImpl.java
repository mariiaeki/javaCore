package homework_8_and_9.cargo.service;

import homework_8_and_9.cargo.comparator.CargoSearchConditions;
import homework_8_and_9.cargo.repo.CargoRepo;
import homework_8_and_9.cargo.domain.BasicCargo;

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
    public List<BasicCargo> getSortedCargos(CargoSearchConditions searchConditions) {
        return cargoRepo.getSortedCargos(searchConditions);
    }


    @Override
    public boolean deleteById(Long id) {
        try {
            return cargoRepo.deleteById(id);
        }catch (NullPointerException e){
            return false;
        }
    }
}
