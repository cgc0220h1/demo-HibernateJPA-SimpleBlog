package app.blog.repositories;

import app.blog.model.Author;
import app.blog.model.Category;
import app.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByCreateTimeBetween(Timestamp startTime, Timestamp endTime);

    List<Post> findPostByCreateTimeBetween(Timestamp startTime, Timestamp endTime, Sort sort);

    Page<Post> findPostByCreateTimeBetween(Timestamp startTime, Timestamp endTime, Pageable pageable);

    List<Post> findPostByAuthor(Author author);

    List<Post> findPostByAuthor(Author author, Sort sort);

    Page<Post> findPostByAuthor(Author author, Pageable pageable);

    List<Post> findPostByCategory(Category category);

    List<Post> findPostByCategory(Category category, Sort sort);

    Page<Post> findPostByCategory(Category category, Pageable pageable);

    List<Post> findPostByContentContains(String content);

    List<Post> findPostByContentContains(String content, Sort sort);

    Page<Post> findPostByContentContains(String content, Pageable pageable);
}
