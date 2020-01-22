package homework_15_2001_concurrency.carrier.repo;

import homework_15_2001_concurrency.carrier.domain.Carrier;
import homework_15_2001_concurrency.common.repository.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier> {

    Carrier[] getByName(String name);

    Carrier getByIdFetchingTransportations(long id);

}
