package homework_8_1812.cargo.repo;

import homework_8_1812.cargo.domain.BasicCargo;
import homework_8_1812.common.repository.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo {
    void add(BasicCargo cargo);

    BasicCargo getById(Long id);

    BasicCargo[] getByName(String name);

    List<BasicCargo> getAll();

    void update(BasicCargo cargo);

    void sortCargosByNameAndWeight();

    void sortCargosByName();

    void sortCargosByWeight();

}
