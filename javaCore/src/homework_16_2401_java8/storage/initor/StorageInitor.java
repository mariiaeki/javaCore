package homework_16_2401_java8.storage.initor;

import homework_16_2401_java8.common.company_exceptions.checked.InitStorageException;

import java.io.IOException;

public interface StorageInitor {
    void storageInitor() throws InitStorageException, IOException;
}
