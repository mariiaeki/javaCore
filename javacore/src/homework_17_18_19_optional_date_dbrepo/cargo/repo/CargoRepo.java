package homework_17_18_19_optional_date_dbrepo.cargo.repo;

import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.common.comparator.EntitySortConditions;
import homework_17_18_19_optional_date_dbrepo.common.repository.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<BasicCargo> {

    List<BasicCargo> getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    Optional<BasicCargo> getByIdFetchingTransportations(long id);

    void addAll(List<BasicCargo> cargos);

}
