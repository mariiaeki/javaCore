package homework_8_and_9.cargo.comparator;

import homework_8_and_9.cargo.domain.CargoField;
import homework_8_and_9.common.search.OrderType;

import java.util.Iterator;
import java.util.Set;

public class CargoSearchConditions {
    private OrderType orderType;
    private Set<CargoField> sortFields;

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Set<CargoField> getSortFields() {
        return sortFields;
    }

    public void setSortFields(Set<CargoField> sortFields) {
        this.sortFields = sortFields;
    }

    public boolean needSorting(){
        return sortFields != null && sortFields.size() > 0;
    }

    public boolean shouldSortByField(CargoField cargoField) {
        return sortFields != null && sortFields.contains(cargoField);
    }

    public boolean isAscOrdering() {
        return orderType == null || OrderType.ASC.equals(orderType);
    }

    public String getOrderingConditionsAsString(){
        StringBuilder result = new StringBuilder();

        Iterator<CargoField> iter = sortFields.iterator();
        while (iter.hasNext()) {
            CargoField fld = iter.next();
            result.append(fld);

            if (iter.hasNext()) {
                result.append(", ");
            }
        }

        return result.toString();
    }

}
