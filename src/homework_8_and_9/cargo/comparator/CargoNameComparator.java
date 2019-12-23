package homework_8_and_9.cargo.comparator;

import homework_8_and_9.cargo.domain.BasicCargo;

import java.util.Comparator;

public class CargoNameComparator implements Comparator<BasicCargo> {
    @Override
    public int compare(BasicCargo cargo1, BasicCargo cargo2) {
        return cargo1.getName().compareTo(cargo2.getName());
    }

}
