package homework_7_1612.cargo.cargoRepo;

import homework_7_1612.cargo.domain.BasicCargo;

import java.util.ArrayList;
import java.util.List;

import static homework_7_1612.common.util.ArrayUtils.copyArray;
import static homework_7_1612.storage.Storage.cargoStorageList;

public class CargoCollectionRepoImpl implements CargoRepo {
    @Override
    public void add(BasicCargo cargo) {
        cargoStorageList.add(cargo);
    }

    @Override
    public BasicCargo getById(long id) {
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
