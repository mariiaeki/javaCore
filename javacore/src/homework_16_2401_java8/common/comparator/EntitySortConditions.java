package homework_16_2401_java8.common.comparator;

import homework_16_2401_java8.common.search.OrderType;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class EntitySortConditions<T> {
    private OrderType orderType;
    private Set<T> sortFields;

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Set<T> getSortFields() {
        return sortFields;
    }

    public void setSortFields(Set<T> sortFields) {
        this.sortFields = sortFields;
    }

    public boolean needSorting(){
        return sortFields != null && sortFields.size() > 0;
    }

    public boolean shouldSortByField(T field) {
        return sortFields != null && sortFields.contains(field);
    }

    public boolean isAscOrdering() {
        return orderType == null || OrderType.ASC.equals(orderType);
    }

    public String getOrderingConditionsAsString(){
        StringBuilder result = new StringBuilder();

        Iterator<T> iter = sortFields.iterator();
        while (iter.hasNext()) {
            T fld = iter.next();
            result.append(fld);

            if (iter.hasNext()) {
                result.append(", ");
            }
        }

        return result.toString();
    }

}
