package service.post;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.PostRepository;

import java.util.List;

@Service
public class PostServiceImp implements PostService {
    private final PostRepository blogRepository;

    @Autowired
    public PostServiceImp(PostRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Post> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public List<Post> findByPage(Pageable pageable) {
        return blogRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Post> findByPage(int startPageIndex, int numberOfElements) {
        Pageable pageable = PageRequest.of(startPageIndex, numberOfElements);
        return blogRepository.findAll(pageable).getContent();
    }

    @Override
    public Page<Post> findPage(int startPageIndex, int numberOfElements) {
        Pageable pageable = PageRequest.of(startPageIndex, numberOfElements);
        return blogRepository.findAll(pageable);
    }

    @Override
    public Post findOne(Long id) {
        if (blogRepository.findById(id).isPresent()) {
            return blogRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Post save(Post post) {
        if (post == null) {
            return null;
        }
        return blogRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        blogRepository.delete(post);
    }

    @Override
    public void delete(Long postId) {
        blogRepository.deleteById(postId);
    }
}
