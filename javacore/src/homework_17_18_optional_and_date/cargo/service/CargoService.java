package homework_17_18_optional_and_date.cargo.service;

import homework_17_18_optional_and_date.cargo.domain.BasicCargo;
import homework_17_18_optional_and_date.common.service.CommonService;
import homework_17_18_optional_and_date.common.comparator.EntitySortConditions;

import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<BasicCargo> {

    List<BasicCargo> getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    Optional<BasicCargo> getByIdFetchingTransportations(Long id);

}
