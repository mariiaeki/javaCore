package homework_16_2401_java8.carrier.repo;

import homework_16_2401_java8.carrier.domain.Carrier;
import homework_16_2401_java8.common.repository.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier> {

    Carrier[] getByName(String name);

    Carrier getByIdFetchingTransportations(long id);

}
