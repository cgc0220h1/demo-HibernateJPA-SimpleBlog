package repository;

import model.Comment;
import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByPost(Post post);

    List<Comment> findCommentByPost(Post post, Sort sort);

    Page<Comment> findCommentByPost(Post post, Pageable pageable);
}
