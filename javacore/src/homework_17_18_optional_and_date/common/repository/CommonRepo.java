package homework_17_18_optional_and_date.common.repository;

import java.util.List;
import java.util.Optional;

public interface CommonRepo<T> {
    boolean deleteById(long id);

    void add(T elem);

    Optional<T> getById(long id);

    List<T> getAll();

    void update(T elem);

}
