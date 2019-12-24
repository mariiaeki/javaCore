package homework_10_2312.carrier.service;

import homework_10_2312.carrier.domain.Carrier;
import homework_10_2312.common.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier> {

    Carrier[] getByName(String name);

    Carrier getByIdFetchingTransportations(Long id);
}
