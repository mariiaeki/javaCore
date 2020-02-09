package homework_17_18_19_optional_date_dbrepo.carrier.repo;

import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;
import homework_17_18_19_optional_date_dbrepo.common.repository.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CarrierRepo extends CommonRepo<Carrier> {

    List<Carrier> getByName(String name);

    Optional<Carrier> getByIdFetchingTransportations(long id);

    void addAll(List<Carrier> carriers);
}
