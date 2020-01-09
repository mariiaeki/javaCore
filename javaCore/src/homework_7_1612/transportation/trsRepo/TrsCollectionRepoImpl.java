package homework_7_1612.transportation.trsRepo;

import homework_7_1612.transportation.domain.Transportation;

import java.util.List;

import static homework_7_1612.storage.Storage.trsStorageList;

public class TrsCollectionRepoImpl implements TrsRepo {
    @Override
    public void add(Transportation trs) {
        trsStorageList.add(trs);
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation trs : trsStorageList) {
            if (trs != null && Long.valueOf(id).equals(trs.getId())) {
                return trs;
            }
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        Integer indexToDelete = findIndexInListStorageById(trsStorageList, id);
        trsStorageList.remove(indexToDelete);

    }

    public static Integer findIndexInListStorageById(List<Transportation> list, long id) {
        for (int i = 0; i < list.size(); i++) {
            if (Long.valueOf(id).equals(list.get(i).getId())) {
                return i;
            }
        }
        return null;
    }
}
