package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<T> {
    List<T> findAll();

    List<T> findByPage(Pageable pageable);

    List<T> findByPage(int startPageIndex, int numberOfElements);

    Page<T> findPage(int startPageIndex, int numberOfElements);

    T findOne(Long id);

    T save(T model);

    void delete(T model);

    void delete(Long id);
}
