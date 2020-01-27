package homework_15_2001_concurrency.cargo.comparator;

import homework_15_2001_concurrency.cargo.domain.BasicCargo;

import java.util.Comparator;

public class CargoNameComparator implements Comparator<BasicCargo> {
    @Override
    public int compare(BasicCargo cargo1, BasicCargo cargo2) {
        return cargo1.getName().compareTo(cargo2.getName());
    }

}
