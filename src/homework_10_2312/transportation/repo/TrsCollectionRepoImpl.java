package homework_10_2312.transportation.repo;

import homework_10_2312.transportation.domain.Transportation;

import java.util.Iterator;
import java.util.List;

import static homework_10_2312.storage.Storage.trsStorageList;

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
    public List<Transportation> getAll() {
        return trsStorageList;
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

    public static Integer findIndexInListStorageById(List<Transportation> list, long id) {
        for (int i = 0; i < list.size(); i++) {
            if (Long.valueOf(id).equals(list.get(i).getId())) {
                return i;
            }
        }
        return null;
    }
}
