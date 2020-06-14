package service.comment;

import model.Comment;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repositories.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findAll(Sort sort) {
        return commentRepository.findAll(sort);
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public List<Comment> findCommentsByPost(Post post) {
        return commentRepository.findCommentByPost(post);
    }

    @Override
    public List<Comment> findCommentsByPost(Post post, Sort sort) {
        return commentRepository.findCommentByPost(post, sort);
    }

    @Override
    public Page<Comment> findCommentSByPost(Post post, Pageable pageable) {
        return commentRepository.findCommentByPost(post, pageable);
    }

    @Override
    public Comment findOne(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
