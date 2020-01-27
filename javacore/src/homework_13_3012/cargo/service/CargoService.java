package homework_13_3012.cargo.service;

import homework_13_3012.cargo.domain.BasicCargo;
import homework_13_3012.common.comparator.EntitySortConditions;
import homework_13_3012.common.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    BasicCargo getByIdFetchingTransportations(Long id);

}
