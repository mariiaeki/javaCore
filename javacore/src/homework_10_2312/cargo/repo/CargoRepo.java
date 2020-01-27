package homework_10_2312.cargo.repo;

import homework_10_2312.common.comparator.EntitySortConditions;
import homework_10_2312.cargo.domain.BasicCargo;
import homework_10_2312.common.repository.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    BasicCargo getByIdFetchingTransportations(long id);

}
