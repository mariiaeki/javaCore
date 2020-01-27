package homework_16_2401_java8.cargo.repo;

import homework_16_2401_java8.cargo.domain.BasicCargo;
import homework_16_2401_java8.common.comparator.EntitySortConditions;
import homework_16_2401_java8.common.util.CollectionUtils;

import java.util.*;

import static homework_16_2401_java8.common.repository.CommonRepoHelpMethods.findIndexInArrayStorageById;
import static homework_16_2401_java8.common.util.ArrayUtils.copyArray;
import static homework_16_2401_java8.storage.Storage.*;


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
    public boolean deleteById(long id) throws NullPointerException {
        Iterator<BasicCargo> iter = cargoStorageList.iterator();

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

    @Override
    public BasicCargo getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public List<BasicCargo> getAll() {
        return Arrays.asList(cargoStorage);
    }

    @Override
    public void update(BasicCargo cargo) {

    }

    @Override
    public List<BasicCargo> getSortedCargos(EntitySortConditions searchConditions) {
        List<BasicCargo> cargos = getAll();

        if (CollectionUtils.isNotEmpty(cargos)) {
            if (searchConditions.needSorting()) {
                switch (searchConditions.getOrderingConditionsAsString()) {
                    case "NAME":
                        cargos.sort(searchConditions.isAscOrdering() ? (Comparator.comparing(BasicCargo::getName)) :
                                (Comparator.comparing(BasicCargo::getName)).reversed());
                    case "WEIGHT":
                        cargos.sort(searchConditions.isAscOrdering() ? (Comparator.comparing(BasicCargo::getWeight)) :
                                (Comparator.comparing(BasicCargo::getWeight)).reversed());

                    case "NAME, WEIGHT":
                        cargos.sort(searchConditions.isAscOrdering() ?
                                (Comparator.comparing(BasicCargo::getName).thenComparing(BasicCargo::getWeight)) :
                                (Comparator.comparing(BasicCargo::getName).thenComparing(BasicCargo::getWeight))
                                        .reversed());
                }
            }
        }
        return cargos;

    }

}
