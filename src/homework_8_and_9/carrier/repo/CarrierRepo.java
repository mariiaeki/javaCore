package homework_8_and_9.carrier.repo;

import homework_8_and_9.carrier.domain.Carrier;
import homework_8_and_9.common.repository.CommonRepo;

import java.util.List;

public interface CarrierRepo extends CommonRepo {
    void add(Carrier carrier);

    Carrier getById(long id);

    Carrier[] getByName(String name);

    List<Carrier> getAll();

    void update(Carrier carrier);

}
