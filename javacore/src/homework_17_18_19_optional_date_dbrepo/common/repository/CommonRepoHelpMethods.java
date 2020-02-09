package homework_17_18_19_optional_date_dbrepo.common.repository;

import homework_17_18_19_optional_date_dbrepo.common.domain.BaseEntity;

public final class CommonRepoHelpMethods <T> {
    public CommonRepoHelpMethods() {
    }

    public static Integer findIndexInArrayStorageById(BaseEntity[] data, long entityId) {
        for (int i = 0; i < data.length; i++) {
            if (Long.valueOf(entityId).equals(data[i].getId())) {
                return i;
            }
        }

        return null;
    }

}
