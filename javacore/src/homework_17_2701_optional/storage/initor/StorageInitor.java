package homework_17_2701_optional.storage.initor;

import homework_17_2701_optional.common.company_exceptions.checked.InitStorageException;

import java.io.IOException;

public interface StorageInitor {
    void storageInitor() throws InitStorageException, IOException;
}
