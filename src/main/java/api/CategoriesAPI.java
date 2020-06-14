package api;

import model.Category;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.category.CategoryService;
import service.post.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesAPI {
    private final CategoryService categoryService;

    private final PostService postService;

    @Autowired
    public CategoriesAPI(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Category> getCategories(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
        return categoryService.findAll(pageable).getContent();
    }

    @GetMapping(value = "/{id}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Post> getPostsByCategoryId(@PathVariable("id") Long id, Pageable pageable) {
        Category category = categoryService.findOne(id);
        return postService.findByCategory(category, pageable).getContent();
    }

}
