package homework_7_1612.transportation.trsRepo;

import homework_7_1612.storage.IdGenerator;
import homework_7_1612.transportation.domain.Transportation;

import static homework_7_1612.common.repository.CommonRepoHelpMethods.findIndexInArrayStorageById;
import static homework_7_1612.common.util.ArrayUtils.copyArray;
import static homework_7_1612.common.util.ArrayUtils.removeElement;
import static homework_7_1612.storage.Storage.*;

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
    public void deleteById(long id) {
        Integer indexToDelete = findIndexInArrayStorageById(transportationsStorage, id);

        removeElement(transportationsStorage, indexToDelete);

    }
}
