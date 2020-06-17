package services.comment;

import model.Comment;
import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import services.GenericService;

import java.util.List;

public interface CommentService extends GenericService<Comment> {
    List<Comment> findCommentsByPost(Post post);

    List<Comment> findCommentsByPost(Post post, Sort sort);

    Page<Comment> findCommentSByPost(Post post, Pageable pageable);
}
