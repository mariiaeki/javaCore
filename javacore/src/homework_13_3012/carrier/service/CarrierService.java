package homework_13_3012.carrier.service;

import homework_13_3012.carrier.domain.Carrier;
import homework_13_3012.common.service.CommonService;

public interface CarrierService extends CommonService<Carrier> {

    Carrier[] getByName(String name);

    Carrier getByIdFetchingTransportations(Long id);
}
