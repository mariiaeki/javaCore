package homework_17_18_optional_and_date.carrier.service;

import homework_17_18_optional_and_date.common.service.CommonService;
import homework_17_18_optional_and_date.carrier.domain.Carrier;

import java.util.List;
import java.util.Optional;

public interface CarrierService extends CommonService<Carrier> {

    List<Carrier> getByName(String name);

    Optional<Carrier> getByIdFetchingTransportations(Long id);
}
