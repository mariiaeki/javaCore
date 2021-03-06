package homework_13_3012.transportation.repo;

import homework_13_3012.storage.IdGenerator;
import homework_13_3012.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static homework_13_3012.common.util.ArrayUtils.copyArray;
import static homework_13_3012.storage.Storage.*;

public class TrsDefaultRepoImpl implements TrsRepo {
    @Override
    public void add(Transportation trs) {
        trs.setId(IdGenerator.generateId());
        transportationsStorage[kTrnsp] = trs;
        kTrnsp++;

        if (kTrnsp % (STORAGE_CAPACITY - 1) == 0) {
            Transportation[] newTrsStorage = new Transportation[kTrnsp + STORAGE_CAPACITY];
            copyArray(transportationsStorage, newTrsStorage);
            transportationsStorage = newTrsStorage;
        }
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation trs : transportationsStorage) {
            if (trs != null && Long.valueOf(id).equals(trs.getId())) {
                return trs;
            }
        }
        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return Arrays.asList(transportationsStorage);
    }

    @Override
    public void update(Transportation trs) {

    }

    @Override
    public boolean deleteById(long id) {
        Iterator<Transportation> iter = trsStorageList.iterator();

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
}
