package homework_7_1612.transportation.trsService;

import homework_7_1612.common.service.CommonService;
import homework_7_1612.transportation.domain.Transportation;

public interface TrsService extends CommonService {
    void add(Transportation trs);

    Transportation getById(long id);
}
