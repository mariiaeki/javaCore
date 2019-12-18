package homework_7_1612.carrier.carrierRepo;

import homework_7_1612.carrier.domain.Carrier;
import homework_7_1612.storage.IdGenerator;

import static homework_7_1612.common.repository.CommonRepoHelpMethods.findIndexInArrayStorageById;
import static homework_7_1612.common.util.ArrayUtils.copyArray;
import static homework_7_1612.common.util.ArrayUtils.removeElement;
import static homework_7_1612.storage.Storage.STORAGE_CAPACITY;
import static homework_7_1612.storage.Storage.kCarrier;
import static homework_7_1612.storage.Storage.carrierStorage;

public class CarrierDefaultRepoImpl implements CarrierRepo {
    @Override
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        carrierStorage[kCarrier] = carrier;
        kCarrier++;

        if (kCarrier % (STORAGE_CAPACITY - 1) == 0) {
            Carrier[] newCarrierStorage = new Carrier[kCarrier + STORAGE_CAPACITY];
            copyArray(carrierStorage, newCarrierStorage);
            carrierStorage = newCarrierStorage;
        }
    }

    @Override
    public Carrier getById(long id) {
        for (int i = 0; i < carrierStorage.length; i++) {
            if (carrierStorage[i] != null && Long.valueOf(id).equals(carrierStorage[i].getId())){
                return carrierStorage[i];
            }
        }
        return null;
    }

    @Override
    public Carrier[] getByName(String name) {
        int arrayLength = 0;
        Carrier[] carrierByName = new Carrier[arrayLength];

        for (Carrier carrier : carrierStorage) {
            if (carrier != null && carrier.getName().equals(name)) {
                if (arrayLength == 0) {
                    Carrier[] newCarrierByName = new Carrier[1];
                    carrierByName = newCarrierByName;
                    carrierByName[0] = carrier;
                    arrayLength++;
                } else {
                    Carrier[] newCarrierByName = new Carrier[arrayLength + 1];
                    copyArray(carrierByName, newCarrierByName);
                    carrierByName = newCarrierByName;
                    carrierByName[arrayLength - 1] = carrier;
                    arrayLength++;
                }
            }
        }

        return carrierByName;
    }

    @Override
    public void deleteById(long id) {
        Integer indexToDelete = findIndexInArrayStorageById(carrierStorage, id);

        removeElement(carrierStorage, indexToDelete);

    }
}
