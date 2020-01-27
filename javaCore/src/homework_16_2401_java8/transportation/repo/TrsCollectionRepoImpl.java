package homework_16_2401_java8.transportation.repo;

import homework_16_2401_java8.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import static homework_16_2401_java8.storage.Storage.trsStorageList;

public class TrsCollectionRepoImpl implements TrsRepo, Serializable {
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

    @Override
    public String toString() {
        return trsStorageList.toString();
    }
}
