package homework_8_and_9.cargo.repo;

import homework_8_and_9.cargo.comparator.CargoSearchConditions;
import homework_8_and_9.cargo.domain.BasicCargo;
import homework_8_and_9.common.repository.*;

import java.util.List;

public interface CargoRepo extends CommonRepo {
    void add(BasicCargo cargo);

    BasicCargo getById(Long id);

    BasicCargo[] getByName(String name);

    List<BasicCargo> getAll();

    void update(BasicCargo cargo);

    List<BasicCargo> getSortedCargos(CargoSearchConditions searchConditions);

    BasicCargo getByIdFetchingTransportations(long id);

}
