package homework_17_2701_optional.storage;

import homework_17_2701_optional.common.domain.BaseEntity;

public final class IdGenerator extends BaseEntity {

    private IdGenerator() {
    }

    private static Long id = 0L;

    public static synchronized Long generateId() {
        return ++id;
    }
}
