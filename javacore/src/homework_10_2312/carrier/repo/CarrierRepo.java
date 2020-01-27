package homework_10_2312.carrier.repo;

import homework_10_2312.carrier.domain.Carrier;
import homework_10_2312.common.repository.CommonRepo;

import java.util.List;

public interface CarrierRepo extends CommonRepo<Carrier>{

    Carrier[] getByName(String name);

    Carrier getByIdFetchingTransportations(long id);

}
