package homework_17_2701_optional.cargo.repo;

import homework_17_2701_optional.cargo.domain.BasicCargo;
import homework_17_2701_optional.common.comparator.EntitySortConditions;
import homework_17_2701_optional.common.repository.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    Optional<BasicCargo> getByIdFetchingTransportations(long id);

}
