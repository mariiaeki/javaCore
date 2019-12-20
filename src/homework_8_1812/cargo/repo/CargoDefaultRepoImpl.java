package homework_8_1812.cargo.repo;

import homework_8_1812.cargo.comparator.CargoNameComparator;
import homework_8_1812.cargo.comparator.CargoWeightComparator;
import homework_8_1812.cargo.domain.BasicCargo;
import homework_8_1812.common.util.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static homework_8_1812.common.repository.CommonRepoHelpMethods.findIndexInArrayStorageById;
import static homework_8_1812.common.util.ArrayUtils.copyArray;
import static homework_8_1812.common.util.ArrayUtils.removeElement;
import static homework_8_1812.storage.Storage.*;


public class CargoDefaultRepoImpl implements CargoRepo {

    @Override
    public void add(BasicCargo cargo) {
        cargoStorage[kCargo] = cargo;
        kCargo++;

        if (kCargo % (STORAGE_CAPACITY - 1) == 0) {
            BasicCargo[] newCargoStorage = new BasicCargo[kCargo + STORAGE_CAPACITY];
            copyArray(cargoStorage, newCargoStorage);
            cargoStorage = newCargoStorage;
        }
    }

    @Override
    public BasicCargo getById(Long id) {
        Integer index = findIndexInArrayStorageById(cargoStorage, id);
        if (index != null) {
            return cargoStorage[index];
        } else {
            return null;
        }
    }

    @Override
    public BasicCargo[] getByName(String name) {
        int arrayLength = 0;
        BasicCargo[] cargoByName = new BasicCargo[arrayLength];

        for (BasicCargo cargo : cargoStorage) {
            if (cargo != null && cargo.getName().equals(name)) {
                if (arrayLength == 0) {
                    BasicCargo[] newCargoByName = new BasicCargo[1];
                    cargoByName = newCargoByName;
                    cargoByName[0] = cargo;
                    arrayLength++;
                } else {
                    BasicCargo[] newCargoByName = new BasicCargo[arrayLength + 1];
                    copyArray(cargoByName, newCargoByName);
                    cargoByName = newCargoByName;
                    cargoByName[arrayLength - 1] = cargo;
                    arrayLength++;
                }
            }
        }

        return cargoByName;
    }

    @Override
    public void deleteById(long id) {
        Integer indexToDelete = findIndexInArrayStorageById(cargoStorage, id);

        removeElement(cargoStorage, indexToDelete);
    }

    @Override
    public List<BasicCargo> getAll(){
        return Arrays.asList(cargoStorage);
    }

    @Override
    public void update(BasicCargo cargo) {

    }

    @Override
    public void sortCargosByNameAndWeight() {
        Comparator cmp = new CargoNameComparator().thenComparing(new CargoWeightComparator());
        Collections.sort(Arrays.asList(cargoStorage), cmp);
    }

    @Override
    public void sortCargosByName() {
        Comparator cmp = new CargoNameComparator();
        Collections.sort(Arrays.asList(cargoStorage), cmp);
    }

    @Override
    public void sortCargosByWeight() {
        Comparator cmp = new CargoWeightComparator();
        Collections.sort(Arrays.asList(cargoStorage), cmp);
    }


}
