package homework_8_1812.transportation.repo;

import homework_8_1812.common.repository.CommonRepo;
import homework_8_1812.transportation.domain.Transportation;

import java.util.List;

public interface TrsRepo extends CommonRepo {
    void add(Transportation trs);

    Transportation getById(long id);

    List<Transportation> getAll();

    void update(Transportation trs);

}
