package service.post;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.IBlogRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PostServiceImpl implements IPostService {
    private final IBlogRepository blogRepository;

    @Autowired
    public PostServiceImpl(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Post> findAllPost() {
        return blogRepository.findAll();
    }

    @Override
    public List<Post> findPostByPage(Pageable pageable) {
        return blogRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Post> findPostByPage(int startPageIndex, int numberOfElements) {
        Pageable pageable = PageRequest.of(startPageIndex, numberOfElements);
        return blogRepository.findAll(pageable).getContent();
    }

    @Override
    public Page<Post> findPostPage(int startPageIndex, int numberOfElements) {
        Pageable pageable = PageRequest.of(startPageIndex, numberOfElements);
        return blogRepository.findAll(pageable);
    }

    @Override
    public Post findOnePost(Long id) {
        if (blogRepository.findById(id).isPresent()) {
            return blogRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Post savePost(Post post) {
        if (post == null) {
            return null;
        }
        return blogRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        blogRepository.delete(post);
    }

    @Override
    public void deletePost(Long postId) {
        blogRepository.deleteById(postId);
    }
}
