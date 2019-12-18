package homework_7_1612.carrier.carrierRepo;

import homework_7_1612.carrier.domain.Carrier;
import homework_7_1612.common.repository.CommonRepo;

public interface CarrierRepo extends CommonRepo {
    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);

}
