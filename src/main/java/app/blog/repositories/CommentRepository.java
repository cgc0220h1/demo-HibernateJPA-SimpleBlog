package app.blog.repositories;

import app.blog.model.Comment;
import app.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByPost(Post post);

    List<Comment> findCommentByPost(Post post, Sort sort);

    Page<Comment> findCommentByPost(Post post, Pageable pageable);
}
