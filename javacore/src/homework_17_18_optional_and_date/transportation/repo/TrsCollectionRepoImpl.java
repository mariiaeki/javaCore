package homework_17_18_optional_and_date.transportation.repo;

import homework_17_18_optional_and_date.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static homework_17_18_optional_and_date.storage.Storage.trsStorageList;

public class TrsCollectionRepoImpl implements TrsRepo, Serializable {
    @Override
    public void add(Transportation trs) {
        trsStorageList.add(trs);
    }

    @Override
    public Optional<Transportation> getById(long id) {
        for (Transportation trs : trsStorageList) {
            if (Long.valueOf(id).equals(trs.getId())) {
                return Optional.of(trs);
            }
        }

        return Optional.empty();
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
        return trsStorageList.removeIf(Transportations -> Transportations.getId().equals(id));

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
