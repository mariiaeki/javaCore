package homework_15_2001_concurrency.storage.initor;

import homework_15_2001_concurrency.common.company_exceptions.checked.InitStorageException;

import java.io.IOException;

public interface StorageInitor {
    void storageInitor() throws InitStorageException, IOException;
}
