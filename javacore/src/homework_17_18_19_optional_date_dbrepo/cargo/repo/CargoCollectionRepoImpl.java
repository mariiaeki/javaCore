package homework_17_18_19_optional_date_dbrepo.cargo.repo;

import homework_17_18_19_optional_date_dbrepo.cargo.domain.BasicCargo;
import homework_17_18_19_optional_date_dbrepo.common.comparator.EntitySortConditions;
import homework_17_18_19_optional_date_dbrepo.common.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static homework_17_18_19_optional_date_dbrepo.storage.Storage.cargoStorageList;

public class CargoCollectionRepoImpl implements CargoRepo, Serializable {
    @Override
    public void add(BasicCargo cargo) {
        cargoStorageList.add(cargo);
    }

    @Override
    public Optional<BasicCargo> getById(long id) {
        for (BasicCargo cargo : cargoStorageList) {
            if (Long.valueOf(id).equals(cargo.getId())) {
                return Optional.of(cargo);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<BasicCargo> getByName(String name) {
        return cargoStorageList.stream().filter(cargo -> cargo.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<BasicCargo> getAll() {
        return cargoStorageList;
    }

    @Override
    public boolean deleteById(long id) {
        return cargoStorageList.removeIf(cargo -> cargo.getId().equals(id));
    }

    @Override
    public Optional<BasicCargo> getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public void addAll(List<BasicCargo> cargos) {

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
                        cargos.sort(searchConditions.isAscOrdering() ?
                                (Comparator.comparing(BasicCargo::getName)) :
                                (Comparator.comparing(BasicCargo::getName)).reversed());
                        break;
                    case "WEIGHT":
                        cargos.sort(searchConditions.isAscOrdering() ?
                                (Comparator.comparing(BasicCargo::getWeight)) :
                                (Comparator.comparing(BasicCargo::getWeight)).reversed());
                        break;

                    case "NAME, WEIGHT":
                        cargos.sort(searchConditions.isAscOrdering() ?
                                (Comparator.comparing(BasicCargo::getName)
                                        .thenComparing(BasicCargo::getWeight)) :
                                (Comparator.comparing(BasicCargo::getName)
                                        .thenComparing(BasicCargo::getWeight))
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
