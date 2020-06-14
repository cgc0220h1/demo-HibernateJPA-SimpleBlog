package api;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.post.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsAPI {
    private final PostService postService;

    @Autowired
    public PostsAPI(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Post> getPosts(Pageable pageable) {
        return postService.findAll(pageable).getContent();
    }
}
