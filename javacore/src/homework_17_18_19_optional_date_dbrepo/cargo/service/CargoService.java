package homework_17_18_19_optional_date_dbrepo.cargo.service;

import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;
import homework_17_18_19_optional_date_dbrepo.common.service.CommonService;
import homework_17_18_19_optional_date_dbrepo.common.comparator.EntitySortConditions;

import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<BasicCargo> {

    List<BasicCargo> getByName(String name);

    List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions);

    Optional<BasicCargo> getByIdFetchingTransportations(Long id);

    void addAll(List<BasicCargo> cargos);

    void addCargosAndCarriers(List<BasicCargo> cargos, List<Carrier> carriers);

}
