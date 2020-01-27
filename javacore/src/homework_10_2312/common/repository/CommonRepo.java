package homework_10_2312.common.repository;

import homework_10_2312.common.comparator.EntitySortConditions;

import java.util.List;

public interface CommonRepo<T> {
    boolean deleteById(long id);

    void add(T elem);

    T getById(long id);

    List<T> getAll();

    void update(T elem);

}
