package homework_16_2401_java8.storage;

import homework_16_2401_java8.common.domain.BaseEntity;

public final class IdGenerator extends BaseEntity {

    private IdGenerator() {
    }

    private static Long id = 0L;

    public static synchronized Long generateId() {
        return ++id;
    }
}
