package homework_8_1812.cargo.comparator;

import homework_8_1812.cargo.domain.BasicCargo;

import java.util.Comparator;

public class CargoWeightComparator implements Comparator<BasicCargo> {

    @Override
    public int compare(BasicCargo cargo1, BasicCargo cargo2) {
        if (cargo1.getWeight() < cargo2.getWeight()) {
            return -1;
        } else if (cargo1.getWeight() > cargo2.getWeight()){
            return 1;
        } else
            return 0;
    }
}
