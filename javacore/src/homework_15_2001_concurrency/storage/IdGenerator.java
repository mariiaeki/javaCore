package homework_15_2001_concurrency.storage;

import homework_15_2001_concurrency.common.domain.BaseEntity;

public final class IdGenerator extends BaseEntity {

    private IdGenerator() {
    }

    private static Long id = 0L;

    public static synchronized Long generateId() {
        return ++id;
    }
}
