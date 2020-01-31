package homework_17_18_optional_and_date.storage.initor;

import homework_17_18_optional_and_date.common.company_exceptions.checked.InitStorageException;

import java.io.IOException;

public interface StorageInitor {
    void storageInitor() throws InitStorageException, IOException;
}
