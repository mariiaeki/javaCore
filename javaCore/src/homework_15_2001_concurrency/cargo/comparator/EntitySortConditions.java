package homework_15_2001_concurrency.cargo.comparator;

import homework_15_2001_concurrency.cargo.domain.CargoField;
import homework_15_2001_concurrency.common.search.OrderType;

import java.util.Iterator;
import java.util.Set;

public class EntitySortConditions {
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

    public boolean shouldSortByField(CargoField field) {
        return sortFields != null && sortFields.contains(field);
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
