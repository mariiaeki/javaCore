package homework_15_2001_concurrency.carrier.repo;

import homework_15_2001_concurrency.carrier.domain.Carrier;
import homework_15_2001_concurrency.storage.IdGenerator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static homework_15_2001_concurrency.common.util.ArrayUtils.copyArray;
import static homework_15_2001_concurrency.storage.Storage.*;

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
    public List<Carrier> getAll() {
        return Arrays.asList(carrierStorage);
    }

    @Override
    public void update(Carrier carrier) {

    }

    @Override
    public boolean deleteById(long id) {
        Iterator<Carrier> iter = carrierStorageList.iterator();

        boolean removed = false;
        while (iter.hasNext()) {
            if (Long.valueOf(id).equals(iter.next().getId())) {
                iter.remove();
                removed = true;
                break;
            }
        }

        return removed;

    }

    @Override
    public Carrier getByIdFetchingTransportations(long id) {
        return getById(id);
    }
}
