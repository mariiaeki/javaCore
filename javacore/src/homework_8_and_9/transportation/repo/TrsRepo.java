package homework_8_and_9.transportation.repo;

import homework_8_and_9.common.repository.CommonRepo;
import homework_8_and_9.transportation.domain.Transportation;

import java.util.List;

public interface TrsRepo extends CommonRepo {
    void add(Transportation trs);

    Transportation getById(long id);

    List<Transportation> getAll();

    void update(Transportation trs);

}
