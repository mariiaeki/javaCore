package homework_17_18_19_optional_date_dbrepo.storage.initor;

import homework_17_18_19_optional_date_dbrepo.common.company_exceptions.checked.InitStorageException;

import java.io.IOException;

public interface StorageInitor {
    void storageInitor() throws InitStorageException, IOException;
}
