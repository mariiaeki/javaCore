package homework_17_2701_optional.cargo.service;

import homework_17_2701_optional.cargo.domain.BasicCargo;
import homework_17_2701_optional.common.service.CommonService;
import homework_17_2701_optional.common.comparator.EntitySortConditions;

import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    Optional<BasicCargo> getByIdFetchingTransportations(long id);

}
