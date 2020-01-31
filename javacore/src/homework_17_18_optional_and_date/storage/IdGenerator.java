package homework_17_18_optional_and_date.storage;

import homework_17_18_optional_and_date.common.domain.BaseEntity;

public final class IdGenerator extends BaseEntity {

    private IdGenerator() {
    }

    private static Long id = 0L;

    public static synchronized Long generateId() {
        return ++id;
    }
}
