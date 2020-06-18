package app.blog.services.post;

import app.blog.model.Author;
import app.blog.model.Category;
import app.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import app.blog.services.GenericService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
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

    List<Post> findByContent(String content);

    List<Post> findByContent(String content, Sort sort);

    Page<Post> findByContent(String content, Pageable pageable);
}
