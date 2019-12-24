package homework_10_2312.carrier.repo;

import homework_10_2312.carrier.domain.Carrier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static homework_10_2312.storage.Storage.carrierStorageList;

public class CarrierCollectionRepoImpl implements CarrierRepo {
    @Override
    public void add(Carrier carrier) {
        carrierStorageList.add(carrier);
    }

    @Override
    public Carrier getById(long id) {
        for (Carrier carrier : carrierStorageList) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }
        return null;
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
        Iterator<Carrier> iter = carrierStorageList.iterator();

        boolean removed = false;
        while (iter.hasNext()) {
            if (Long.valueOf(id).equals(iter.next().getId())) {
                iter.remove();
                removed = true;
                break;
            }
        }
        return removed;
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
    public Carrier getByIdFetchingTransportations(long id) {
        return getById(id);
    }
}
