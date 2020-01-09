package homework_7_1612.cargo.cargoRepo;

import homework_7_1612.cargo.domain.BasicCargo;

import static homework_7_1612.common.repository.CommonRepoHelpMethods.findIndexInArrayStorageById;
import static homework_7_1612.common.util.ArrayUtils.copyArray;
import static homework_7_1612.common.util.ArrayUtils.removeElement;
import static homework_7_1612.storage.Storage.STORAGE_CAPACITY;
import static homework_7_1612.storage.Storage.kCargo;
import static homework_7_1612.storage.Storage.cargoStorage;

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
    public BasicCargo getById(long id) {
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
}
