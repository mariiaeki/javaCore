package homework_8_1812.cargo.repo;

import homework_8_1812.cargo.comparator.CargoNameComparator;
import homework_8_1812.cargo.comparator.CargoWeightComparator;
import homework_8_1812.cargo.domain.BasicCargo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static homework_8_1812.storage.Storage.cargoStorageList;

public class CargoCollectionRepoImpl implements CargoRepo {
    @Override
    public void add(BasicCargo cargo) {
        cargoStorageList.add(cargo);
    }

    @Override
    public BasicCargo getById(Long id) {
        for (BasicCargo cargo : cargoStorageList) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }
        return null;
    }

    @Override
    public BasicCargo[] getByName(String name) {

        List<BasicCargo> cargoByNameList = new ArrayList<>();

        for (BasicCargo cargo : cargoStorageList) {
           if (cargo != null && cargo.getName().equals(name)){
               cargoByNameList.add(cargo);
           }
        }

        BasicCargo[] cargoByName = cargoByNameList.toArray( new BasicCargo[cargoByNameList.size()]);

        return cargoByName;
    }

    @Override
    public List<BasicCargo> getAll() {
        return cargoStorageList;
    }

    @Override
    public void update(BasicCargo cargo) {

    }

    @Override
    public void sortCargosByNameAndWeight() {
        Comparator cmp = new CargoNameComparator().thenComparing(new CargoWeightComparator());
        Collections.sort(cargoStorageList, cmp);
    }

    @Override
    public void sortCargosByName() {
        Comparator cmp = new CargoNameComparator();
        Collections.sort(cargoStorageList, cmp);
    }

    @Override
    public void sortCargosByWeight() {
        Comparator cmp = new CargoWeightComparator();
        Collections.sort(cargoStorageList, cmp);
    }

    @Override
    public void deleteById(long id) {
        Integer indexToDelete = findIndexInListStorageById(cargoStorageList, id);
        cargoStorageList.remove(indexToDelete);

    }

    public static Integer findIndexInListStorageById(List<BasicCargo> list, long id) {
        for (int i = 0; i < list.size(); i++) {
            if (Long.valueOf(id).equals(list.get(i).getId())) {
                return i;
            }
        }
        return null;
    }

}
