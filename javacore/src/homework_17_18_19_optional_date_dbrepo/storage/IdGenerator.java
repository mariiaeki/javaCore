package homework_17_18_19_optional_date_dbrepo.storage;

import homework_17_18_19_optional_date_dbrepo.common.domain.BaseEntity;

public final class IdGenerator extends BaseEntity {

    private IdGenerator() {
    }

    private static Long id = 20L;

    public static synchronized Long generateId() {
        return ++id;
    }
}
