package homework_10_2312.common.service;

import homework_10_2312.common.comparator.EntitySortConditions;

import java.util.List;

public interface CommonService<T> {
    boolean deleteById(Long id);

    void add(T elem);

    T getById(Long id);

    List<T> getAll();

    void update(T elem);

    void printAll();

}
