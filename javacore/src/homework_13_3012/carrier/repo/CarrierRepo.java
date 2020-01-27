package homework_13_3012.carrier.repo;

import homework_13_3012.carrier.domain.Carrier;
import homework_13_3012.common.repository.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier> {

    Carrier[] getByName(String name);

    Carrier getByIdFetchingTransportations(long id);

}
