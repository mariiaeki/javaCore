package homework_10_2312.cargo.service;

import homework_10_2312.common.comparator.EntitySortConditions;
import homework_10_2312.cargo.domain.BasicCargo;
import homework_10_2312.common.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    BasicCargo getByIdFetchingTransportations(Long id);

}
