package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IService<T> {
    List<T> findAllPost();

    List<T> findPostByPage(Pageable pageable);

    List<T> findPostByPage(int startPageIndex, int numberOfElements);

    Page<T> findPostPage(int startPageIndex, int numberOfElements);

    T findOnePost(Long id);

    T savePost(T model);

    void deletePost(T model);

    void deletePost(Long id);
}
