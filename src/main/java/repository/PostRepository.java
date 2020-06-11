package repository;

import model.Author;
import model.Category;
import model.Comment;
import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
}
