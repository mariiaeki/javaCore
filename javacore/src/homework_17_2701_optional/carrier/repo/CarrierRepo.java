package homework_17_2701_optional.carrier.repo;

import homework_17_2701_optional.carrier.domain.Carrier;
import homework_17_2701_optional.common.repository.CommonRepo;

import java.util.Optional;

public interface CarrierRepo extends CommonRepo<Carrier> {

    Carrier[] getByName(String name);

    Optional<Carrier> getByIdFetchingTransportations(long id);

}
