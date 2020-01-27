package homework_15_2001_concurrency.cargo.comparator;

import homework_15_2001_concurrency.cargo.domain.BasicCargo;

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
