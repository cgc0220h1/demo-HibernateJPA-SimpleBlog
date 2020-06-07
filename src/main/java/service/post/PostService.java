package service.post;

import model.Author;
import model.Category;
import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import service.GenericService;

import java.sql.Timestamp;
import java.util.List;

public interface PostService extends GenericService<Post> {
    List<Post> findInTimeRange(Timestamp startTime, Timestamp endTime);

    List<Post> findInTimeRange(Timestamp startTime, Timestamp endTime, Sort sort);

    Page<Post> findInTimeRange(Timestamp start, Timestamp end, Pageable pageable);

    List<Post> findByAuthor(Author author);

    List<Post> findByAuthor(Author author, Sort sort);

    Page<Post> findByAuthor(Author author, Pageable pageable);

    List<Post> findByCategory(Category category);

    List<Post> findByCategory(Category category, Sort sort);

    Page<Post> findByCategory(Category category, Pageable pageable);
}
