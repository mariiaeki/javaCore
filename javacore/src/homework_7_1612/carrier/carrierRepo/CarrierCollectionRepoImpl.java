package homework_7_1612.carrier.carrierRepo;

import homework_7_1612.cargo.domain.BasicCargo;
import homework_7_1612.carrier.domain.Carrier;
import homework_7_1612.common.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import static homework_7_1612.common.util.ArrayUtils.copyArray;
import static homework_7_1612.storage.Storage.carrierStorageList;

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
    public void deleteById(long id) {
        Integer indexToDelete = findIndexInListStorageById(carrierStorageList, id);
        carrierStorageList.remove(indexToDelete);
    }

    public static Integer findIndexInListStorageById(List<Carrier> list, long id) {
        for (int i = 0; i < list.size(); i++) {
            if (Long.valueOf(id).equals(list.get(i).getId())) {
                return i;
            }
        }
        return null;
    }


}
