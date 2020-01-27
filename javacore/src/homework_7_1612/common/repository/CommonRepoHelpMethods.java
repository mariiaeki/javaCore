package homework_7_1612.common.repository;

import homework_7_1612.common.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

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
