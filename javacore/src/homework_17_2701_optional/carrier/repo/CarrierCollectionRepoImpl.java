package homework_17_2701_optional.carrier.repo;

import homework_17_2701_optional.carrier.domain.Carrier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static homework_17_2701_optional.storage.Storage.carrierStorageList;

public class CarrierCollectionRepoImpl implements CarrierRepo, Serializable {
    @Override
    public void add(Carrier carrier) {
        carrierStorageList.add(carrier);
    }

    @Override
    public Optional<Carrier> getById(long id) {
        Optional<Carrier> op = Optional.empty();
        for (Carrier carrier : carrierStorageList) {
            if (Long.valueOf(id).equals(carrier.getId())) {
                op = Optional.ofNullable(carrier);
            }
        }
        return op;
    }

    @Override
    public Carrier[] getByName(String name) {
        List<Carrier> carrierByNameList = new ArrayList<>();

        for (Carrier carrier : carrierStorageList) {
            if (carrier != null && carrier.getName().equals(name)) {
                carrierByNameList.add(carrier);
            }
        }

        Carrier[] carrierByName = carrierByNameList.toArray(new Carrier[carrierByNameList.size()]);

        return carrierByName;
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
        return carrierStorageList.removeIf(Carrier->Carrier.getId().equals(id));
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
    public String toString() {
        return carrierStorageList.toString();
    }
}
