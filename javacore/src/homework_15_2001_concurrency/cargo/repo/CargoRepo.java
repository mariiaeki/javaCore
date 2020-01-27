package homework_15_2001_concurrency.cargo.repo;

import homework_15_2001_concurrency.cargo.domain.BasicCargo;
import homework_15_2001_concurrency.common.comparator.EntitySortConditions;
import homework_15_2001_concurrency.common.repository.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    BasicCargo getByIdFetchingTransportations(long id);

}
