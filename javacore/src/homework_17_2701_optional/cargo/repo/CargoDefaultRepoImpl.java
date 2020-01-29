package homework_17_2701_optional.cargo.repo;

import homework_17_2701_optional.cargo.domain.BasicCargo;
import homework_17_2701_optional.common.comparator.EntitySortConditions;
import homework_17_2701_optional.common.util.CollectionUtils;

import java.util.*;

import static homework_17_2701_optional.common.repository.CommonRepoHelpMethods.findIndexInArrayStorageById;
import static homework_17_2701_optional.common.util.ArrayUtils.copyArray;
import static homework_17_2701_optional.common.util.ArrayUtils.removeElement;
import static homework_17_2701_optional.storage.Storage.*;


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
    public Optional<BasicCargo> getById(long id) {
        return Optional.ofNullable(cargoStorage[findIndexInArrayStorageById(cargoStorage, id)]);
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
    public Optional<BasicCargo> getByIdFetchingTransportations(long id) {
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
