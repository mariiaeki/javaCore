package homework_16_2401_java8.cargo.repo;

import homework_16_2401_java8.cargo.domain.BasicCargo;
import homework_16_2401_java8.common.comparator.EntitySortConditions;
import homework_16_2401_java8.common.repository.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    BasicCargo getByIdFetchingTransportations(long id);

}
