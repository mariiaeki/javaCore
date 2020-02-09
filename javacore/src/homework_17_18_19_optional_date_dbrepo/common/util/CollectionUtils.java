package homework_17_18_19_optional_date_dbrepo.common.util;

import java.util.Collection;

public final class CollectionUtils {

    private CollectionUtils() {

    }

    public static void printCollection(Collection<?> collection) {
        collection.forEach(obj -> System.out.println(obj.toString()));
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

}