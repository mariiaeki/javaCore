package homework_13_3012.cargo.repo;

import homework_13_3012.cargo.domain.BasicCargo;
import homework_13_3012.common.comparator.EntitySortConditions;
import homework_13_3012.common.repository.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    BasicCargo getByIdFetchingTransportations(long id);

}
