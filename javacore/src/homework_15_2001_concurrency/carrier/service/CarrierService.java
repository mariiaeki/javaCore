package homework_15_2001_concurrency.carrier.service;

import homework_15_2001_concurrency.carrier.domain.Carrier;
import homework_15_2001_concurrency.common.service.CommonService;

public interface CarrierService extends CommonService<Carrier> {

    Carrier[] getByName(String name);

    Carrier getByIdFetchingTransportations(Long id);
}
