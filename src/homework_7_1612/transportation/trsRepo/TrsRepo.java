package homework_7_1612.transportation.trsRepo;

import homework_7_1612.common.repository.CommonRepo;
import homework_7_1612.transportation.domain.Transportation;

public interface TrsRepo extends CommonRepo {
    void add(Transportation trs);

    Transportation getById(long id);

}
