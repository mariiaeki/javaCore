package homework_7_1612.cargo.cargoService;

import homework_7_1612.cargo.domain.BasicCargo;

import java.util.List;

public class CargoServiceImpl implements CargoService {
    @Override
    public void add(BasicCargo cargo) {

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
