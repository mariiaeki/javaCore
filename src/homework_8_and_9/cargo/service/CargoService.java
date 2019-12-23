package homework_8_and_9.cargo.service;

import homework_8_and_9.cargo.comparator.CargoSearchConditions;
import homework_8_and_9.cargo.domain.BasicCargo;
import homework_8_and_9.common.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(BasicCargo cargo);

    BasicCargo getById(Long id);

    BasicCargo[] getByName(String name);

    List<BasicCargo> getAll();

    void printAll();

    void update(BasicCargo cargo);

    List<BasicCargo> getSortedCargos(CargoSearchConditions searchConditions);

}
