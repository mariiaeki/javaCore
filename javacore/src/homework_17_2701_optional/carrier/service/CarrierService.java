package homework_17_2701_optional.carrier.service;

import homework_17_2701_optional.common.service.CommonService;
import homework_17_2701_optional.carrier.domain.Carrier;

import java.util.Optional;

public interface CarrierService extends CommonService<Carrier> {

    Carrier[] getByName(String name);

    Optional<Carrier> getByIdFetchingTransportations(Long id);
}
