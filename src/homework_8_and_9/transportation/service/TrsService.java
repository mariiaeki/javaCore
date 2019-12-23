package homework_8_and_9.transportation.service;

import homework_8_and_9.common.service.CommonService;
import homework_8_and_9.transportation.domain.Transportation;

import java.util.List;

public interface TrsService extends CommonService {
    void add(Transportation trs);

    Transportation getById(Long id);

    List<Transportation> getAll();

    void printAll();

    void update(Transportation trs);
}
