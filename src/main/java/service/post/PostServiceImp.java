package service.post;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.PostRepository;

import java.sql.Timestamp;
import java.util.LinkedList;
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
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> findAll(int startPageIndex, int numberOfElements) {
        Pageable pageable = PageRequest.of(startPageIndex, numberOfElements);
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> findInDateRange(Timestamp start, Timestamp end) {
        return postRepository.findPostByCreateTimeBetween(start, end, PageRequest.of(0, 6));
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
