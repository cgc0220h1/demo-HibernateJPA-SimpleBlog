package services.post;

import model.Author;
import model.Category;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repositories.PostRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostServiceImp implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAll(Sort sort) {
        return postRepository.findAll(sort);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Post> findInTimeRange(Timestamp start, Timestamp end) {
        return postRepository.findPostByCreateTimeBetween(start, end);
    }

    @Override
    public List<Post> findInTimeRange(Timestamp startTime, Timestamp endTime, Sort sort) {
        return postRepository.findPostByCreateTimeBetween(startTime, endTime, sort);
    }

    @Override
    public Page<Post> findInTimeRange(Timestamp start, Timestamp end, Pageable pageable) {
        return postRepository.findPostByCreateTimeBetween(start, end, pageable);
    }

    @Override
    public List<Post> findByAuthor(Author author) {
        return postRepository.findPostByAuthor(author);
    }

    @Override
    public List<Post> findByAuthor(Author author, Sort sort) {
        return postRepository.findPostByAuthor(author, sort);
    }

    @Override
    public Page<Post> findByAuthor(Author author, Pageable pageable) {
        return postRepository.findPostByAuthor(author, pageable);
    }

    @Override
    public List<Post> findByContent(String content) {
        return postRepository.findPostByContentContains(content);
    }

    @Override
    public List<Post> findByContent(String content, Sort sort) {
        return postRepository.findPostByContentContains(content, sort);
    }

    @Override
    public Page<Post> findByContent(String content, Pageable pageable) {
        return postRepository.findPostByContentContains(content, pageable);
    }

    @Override
    public List<Post> findByCategory(Category category) {
        return postRepository.findPostByCategory(category);
    }

    @Override
    public List<Post> findByCategory(Category category, Sort sort) {
        return postRepository.findPostByCategory(category, sort);
    }

    @Override
    public Page<Post> findByCategory(Category category, Pageable pageable) {
        return postRepository.findPostByCategory(category, pageable);
    }

    @Override
    public Post findOne(Long id) {
        if (postRepository.findById(id).isPresent()) {
            return postRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Post save(Post post) {
        if (post == null) {
            return null;
        }
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
