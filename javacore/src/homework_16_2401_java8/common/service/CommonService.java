package homework_16_2401_java8.common.service;

import java.util.List;

public interface CommonService<T> {
    boolean deleteById(Long id);

    void add(T elem);

    T getById(Long id);

    List<T> getAll();

    void update(T elem);

    void printAll();

}
