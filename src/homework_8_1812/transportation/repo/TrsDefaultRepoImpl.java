package homework_8_1812.transportation.repo;

import homework_8_1812.storage.IdGenerator;
import homework_8_1812.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.List;

import static homework_8_1812.common.repository.CommonRepoHelpMethods.findIndexInArrayStorageById;
import static homework_8_1812.common.util.ArrayUtils.copyArray;
import static homework_8_1812.common.util.ArrayUtils.removeElement;
import static homework_8_1812.storage.Storage.*;

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
    public void deleteById(long id) {
        Integer indexToDelete = findIndexInArrayStorageById(transportationsStorage, id);

        removeElement(transportationsStorage, indexToDelete);

    }
}
