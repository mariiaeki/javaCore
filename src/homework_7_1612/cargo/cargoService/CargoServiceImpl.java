package homework_7_1612.cargo.cargoService;

import homework_7_1612.cargo.cargoRepo.CargoRepo;
import homework_7_1612.cargo.domain.BasicCargo;

import java.util.List;

public class CargoServiceImpl implements CargoService {
//    private final CargoRepo cargoRepo;
//
//    public CargoServiceImpl(CargoRepo cargoRepo) {
//        this.cargoRepo = cargoRepo;
//    }

    @Override
    public void add(BasicCargo cargo) {
//        if (cargo != null){
//            cargoRepo.add(cargo);
//        }

    }

    @Override
    public BasicCargo getById(long id) {
        return null;
    }

    @Override
    public BasicCargo[] getByName(String name) {
        return new BasicCargo[0];
    }

    @Override
    public void deleteById(Long id) {
        deleteById(id);
    }
}
