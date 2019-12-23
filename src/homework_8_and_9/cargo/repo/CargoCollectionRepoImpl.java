package homework_8_and_9.cargo.repo;

import homework_8_and_9.cargo.comparator.CargoNameComparator;
import homework_8_and_9.cargo.comparator.CargoSearchConditions;
import homework_8_and_9.cargo.comparator.CargoWeightComparator;
import homework_8_and_9.cargo.domain.BasicCargo;
import homework_8_and_9.common.util.CollectionUtils;

import java.util.*;

import static homework_8_and_9.storage.Storage.cargoStorageList;

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
            if (cargo != null && cargo.getName().equals(name)) {
                cargoByNameList.add(cargo);
            }
        }

        BasicCargo[] cargoByName = cargoByNameList.toArray(new BasicCargo[cargoByNameList.size()]);

        return cargoByName;
    }

    @Override
    public List<BasicCargo> getAll() {
        return cargoStorageList;
    }

    @Override
    public boolean deleteById(long id) throws NullPointerException{
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
    public void update(BasicCargo cargo) {

    }

    @Override
    public List<BasicCargo> getSortedCargos(CargoSearchConditions searchConditions) {
        List<BasicCargo> cargos = getAll();

        if (CollectionUtils.isNotEmpty(cargos)) {
            if (searchConditions.needSorting()) {
                switch (searchConditions.getOrderingConditionsAsString()) {
                    case "NAME":
                        Comparator cmpN = new CargoNameComparator();
                        Collections.sort(cargos, searchConditions.isAscOrdering() ? cmpN : cmpN.reversed());
                    case "WEIGHT":
                        Comparator cmpW = new CargoWeightComparator();
                        Collections.sort(cargos, searchConditions.isAscOrdering() ? cmpW : cmpW.reversed());
                    case "NAME, WEIGHT":
                        Comparator cmpNW = new CargoNameComparator().thenComparing(new CargoWeightComparator());
                        Collections.sort(cargos, searchConditions.isAscOrdering() ? cmpNW : cmpNW.reversed());
                }
            }
        }
        return cargos;
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
