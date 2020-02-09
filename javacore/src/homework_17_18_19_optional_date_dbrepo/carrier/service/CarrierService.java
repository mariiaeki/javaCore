package homework_17_18_19_optional_date_dbrepo.carrier.service;

import homework_17_18_19_optional_date_dbrepo.common.service.CommonService;
import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;

import java.util.List;
import java.util.Optional;

public interface CarrierService extends CommonService<Carrier> {

    List<Carrier> getByName(String name);

    Optional<Carrier> getByIdFetchingTransportations(Long id);

    void addAll(List<Carrier> carriers);

}
