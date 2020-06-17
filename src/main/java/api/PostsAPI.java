package api;

import model.Category;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import services.category.CategoryService;
import services.post.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsAPI {
    private final PostService postService;

    private final CategoryService categoryService;

    @Autowired
    public PostsAPI(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Post> getPosts(@RequestParam(value = "content", defaultValue = "") String content,
                               Pageable pageable) {
        List<Post> posts;
        if (!content.equals("")) {
            posts = postService.findByContent(content, pageable).getContent();
        } else {
            posts = postService.findAll(pageable).getContent();
        }
        return posts;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Post getPostDetailById(@PathVariable("id") Long id) {
        return postService.findOne(id);
    }

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Post> getPostByCategoryName(@RequestParam(value = "name", defaultValue = "") String name,
                                            Pageable pageable) {
        Category category = categoryService.findByName(name);
        return postService.findByCategory(category, pageable).getContent();
    }
}
