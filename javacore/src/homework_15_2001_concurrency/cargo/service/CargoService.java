package homework_15_2001_concurrency.cargo.service;

import homework_15_2001_concurrency.cargo.domain.BasicCargo;
import homework_15_2001_concurrency.common.comparator.EntitySortConditions;
import homework_15_2001_concurrency.common.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    BasicCargo getByIdFetchingTransportations(Long id);

}
