package homework_10_2312.storage;

import homework_10_2312.common.domain.BaseEntity;

public final class IdGenerator extends BaseEntity {

    private IdGenerator() {
    }

    private static Long id = 0L;

    public static Long generateId() {
        return ++id;
    }
}
