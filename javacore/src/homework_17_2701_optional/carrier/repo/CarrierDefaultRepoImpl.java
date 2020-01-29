package homework_17_2701_optional.carrier.repo;

import homework_17_2701_optional.carrier.domain.Carrier;
import homework_17_2701_optional.storage.IdGenerator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static homework_17_2701_optional.common.repository.CommonRepoHelpMethods.findIndexInArrayStorageById;
import static homework_17_2701_optional.common.util.ArrayUtils.copyArray;
import static homework_17_2701_optional.storage.Storage.*;

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
    public Optional<Carrier> getById(long id) {
        return Optional.ofNullable(carrierStorage[findIndexInArrayStorageById(carrierStorage,id)]);

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
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return getById(id);
    }
}
