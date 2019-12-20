package homework_8_1812.cargo.service;

import homework_8_1812.cargo.domain.BasicCargo;
import homework_8_1812.common.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(BasicCargo cargo);

    BasicCargo getById(Long id);

    BasicCargo[] getByName(String name);

    List<BasicCargo> getAll();

    void printAll();

    void update(BasicCargo cargo);

    void sortCargosByNameAndWeight();

    void sortCargosByName();

    void sortCargosByWeight();
}
