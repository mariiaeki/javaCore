package homework_17_18_19_optional_date_dbrepo.carrier.repo;

import homework_17_18_19_optional_date_dbrepo.carrier.domain.Carrier;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static homework_17_18_19_optional_date_dbrepo.storage.Storage.carrierStorageList;

public class CarrierCollectionRepoImpl implements CarrierRepo, Serializable {
    @Override
    public void add(Carrier carrier) {
        carrierStorageList.add(carrier);
    }

    @Override
    public Optional<Carrier> getById(long id) {
        for (Carrier carrier : carrierStorageList) {
            if (Long.valueOf(id).equals(carrier.getId())) {
                return Optional.of(carrier);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Carrier> getByName(String name) {
        return carrierStorageList.stream().filter((carrier) -> carrier.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Carrier> getAll() {
        return carrierStorageList;
    }

    @Override
    public void update(Carrier carrier) {

    }

    @Override
    public boolean deleteById(long id) {
        return carrierStorageList
                .removeIf(carrier -> carrier.getId().equals(id));
    }

    public static Integer findIndexInListStorageById(List<Carrier> list, long id) {
        for (int i = 0; i < list.size(); i++) {
            if (Long.valueOf(id).equals(list.get(i).getId())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public void addAll(List<Carrier> carriers) {

    }

    @Override
    public String toString() {
        return carrierStorageList.toString();
    }
}
