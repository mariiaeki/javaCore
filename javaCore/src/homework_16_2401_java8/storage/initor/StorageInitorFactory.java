package homework_16_2401_java8.storage.initor;

import homework_16_2401_java8.storage.initor.fileinitor.StorageInitorInTextFileImpl;
import homework_16_2401_java8.storage.initor.fileinitor.StorageInitorInXML;
import homework_16_2401_java8.storage.initor.fileinitor.StorageInitorInXMLWithSAX;

public final class StorageInitorFactory {

  private StorageInitorFactory(){

  }

  public static StorageInitor getStorageInitor(InitStorageType initStorageType){
    switch (initStorageType){

      case MEMORY: {
        return new StorageInitorInMemoryImpl();
      }
      case TEXT_FILE: {
        return new StorageInitorInTextFileImpl();
      }
      case XML_FILE:{
        return new StorageInitorInXML();
      }
      case XML_FILE_SAX:{
        return new StorageInitorInXMLWithSAX();
      }
      default:{
        throw new RuntimeException("Unknown storage init type " + initStorageType);
      }
    }
  }

}
