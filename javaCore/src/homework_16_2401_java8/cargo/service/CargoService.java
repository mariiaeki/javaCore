package homework_16_2401_java8.cargo.service;

import homework_16_2401_java8.cargo.domain.BasicCargo;
import homework_16_2401_java8.common.comparator.EntitySortConditions;
import homework_16_2401_java8.common.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<BasicCargo> {

    BasicCargo[] getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    BasicCargo getByIdFetchingTransportations(Long id);

}
