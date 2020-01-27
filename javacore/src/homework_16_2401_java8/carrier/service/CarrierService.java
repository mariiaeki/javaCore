package homework_16_2401_java8.carrier.service;

import homework_16_2401_java8.carrier.domain.Carrier;
import homework_16_2401_java8.common.service.CommonService;

public interface CarrierService extends CommonService<Carrier> {

    Carrier[] getByName(String name);

    Carrier getByIdFetchingTransportations(Long id);
}
