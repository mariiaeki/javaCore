package homework_17_18_optional_and_date.common.repository;

import homework_17_18_optional_and_date.common.domain.BaseEntity;

public final class CommonRepoHelpMethods {
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
