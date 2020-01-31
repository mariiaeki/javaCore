package homework_17_18_optional_and_date.carrier.repo;

import homework_17_18_optional_and_date.carrier.domain.Carrier;
import homework_17_18_optional_and_date.common.repository.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CarrierRepo extends CommonRepo<Carrier> {

    List<Carrier> getByName(String name);

    Optional<Carrier> getByIdFetchingTransportations(long id);

}
