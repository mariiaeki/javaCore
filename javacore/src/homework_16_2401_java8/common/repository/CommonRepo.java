package homework_16_2401_java8.common.repository;

import java.util.List;

public interface CommonRepo<T> {
    boolean deleteById(long id);

    void add(T elem);

    T getById(long id);

    List<T> getAll();

    void update(T elem);

}
