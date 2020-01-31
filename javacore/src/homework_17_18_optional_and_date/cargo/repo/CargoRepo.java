package homework_17_18_optional_and_date.cargo.repo;

import homework_17_18_optional_and_date.cargo.domain.BasicCargo;
import homework_17_18_optional_and_date.common.comparator.EntitySortConditions;
import homework_17_18_optional_and_date.common.repository.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<BasicCargo> {

    List<BasicCargo> getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    Optional<BasicCargo> getByIdFetchingTransportations(long id);

}
