package homework_7_1612.cargo.cargoRepo;

import homework_7_1612.cargo.domain.BasicCargo;
import homework_7_1612.common.repository.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo {
    void add(BasicCargo cargo);

    BasicCargo getById(long id);

    BasicCargo[] getByName(String name);

}
