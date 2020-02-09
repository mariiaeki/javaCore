package homework_17_18_19_optional_date_dbrepo.common.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T> {
    boolean deleteById(Long id);

    void add(T elem);

    Optional<T> getById(Long id);

    List<T> getAll();

    void update(T elem);

    void printAll();

}
