package homework_17_2701_optional.cargo.repo;

import homework_17_2701_optional.cargo.domain.BasicCargo;
import homework_17_2701_optional.common.comparator.EntitySortConditions;
import homework_17_2701_optional.common.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

import static homework_17_2701_optional.storage.Storage.cargoStorageList;

public class CargoCollectionRepoImpl implements CargoRepo, Serializable {
    @Override
    public void add(BasicCargo cargo) {
        cargoStorageList.add(cargo);
    }

    @Override
    public Optional<BasicCargo> getById(long id) {
        Optional<BasicCargo> op = Optional.empty();
        for (BasicCargo cargo : cargoStorageList) {
            if (Long.valueOf(id).equals(cargo.getId())) {
                op = Optional.ofNullable(cargo);
            }
        }
        return op;
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
    public boolean deleteById(long id) {
        return cargoStorageList.removeIf(BasicCargo->BasicCargo.getId().equals(id));
    }

    @Override
    public Optional<BasicCargo> getByIdFetchingTransportations(long id) {
        return getById(id);
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
                        break;
                    case "WEIGHT":
                        cargos.sort(searchConditions.isAscOrdering() ? (Comparator.comparing(BasicCargo::getWeight)) :
                                (Comparator.comparing(BasicCargo::getWeight)).reversed());
                        break;

                    case "NAME, WEIGHT":
                        cargos.sort(searchConditions.isAscOrdering() ?
                                (Comparator.comparing(BasicCargo::getName).thenComparing(BasicCargo::getWeight)) :
                                (Comparator.comparing(BasicCargo::getName).thenComparing(BasicCargo::getWeight))
                                        .reversed());
                        break;
                }

            }
        }
        return cargos;
    }

    @Override
    public String toString() {
        return cargoStorageList.toString();
    }
}
