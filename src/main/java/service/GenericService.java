package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.List;

public interface GenericService<T> {
    List<T> findAll();

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(int startPageIndex, int numberOfElements);

    Page<T> findInDateRange(Timestamp start, Timestamp end);

    T findOne(Long id);

    T save(T model);

    void delete(T model);

    void delete(Long id);

}
