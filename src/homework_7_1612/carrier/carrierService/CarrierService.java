package homework_7_1612.carrier.carrierService;

import homework_7_1612.carrier.domain.Carrier;
import homework_7_1612.common.service.CommonService;

public interface CarrierService extends CommonService {
    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);
}
