package app.blog.services.comment;

import app.blog.model.Comment;
import app.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import app.blog.services.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService extends GenericService<Comment> {
    List<Comment> findCommentsByPost(Post post);

    List<Comment> findCommentsByPost(Post post, Sort sort);

    Page<Comment> findCommentSByPost(Post post, Pageable pageable);
}
