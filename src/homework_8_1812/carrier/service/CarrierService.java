package homework_8_1812.carrier.service;

import homework_8_1812.carrier.domain.Carrier;
import homework_8_1812.common.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    Carrier[] getByName(String name);

    List<Carrier> getAll();

    void printAll();

    void update(Carrier carrier);
}
