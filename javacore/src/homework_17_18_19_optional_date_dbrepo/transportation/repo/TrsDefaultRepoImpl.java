package homework_17_18_19_optional_date_dbrepo.transportation.repo;

import homework_17_18_19_optional_date_dbrepo.storage.IdGenerator;
import homework_17_18_19_optional_date_dbrepo.transportation.domain.Transportation;
import static homework_17_18_19_optional_date_dbrepo.common.repository.CommonRepoHelpMethods.findIndexInArrayStorageById;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static homework_17_18_19_optional_date_dbrepo.common.util.ArrayUtils.copyArray;
import static homework_17_18_19_optional_date_dbrepo.storage.Storage.*;

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
    public Optional<Transportation> getById(long id) {

        return Optional.ofNullable(transportationsStorage[findIndexInArrayStorageById(transportationsStorage,id)]);
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
