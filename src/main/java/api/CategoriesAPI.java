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

import java.util.LinkedList;
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
    public List<Category> getCategories(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.DESC) Pageable pageable,
                                        @RequestParam(value = "name", defaultValue = "") String name) {
        List<Category> categories = new LinkedList<>();
        if (!name.equals("")) {
            categories.add(categoryService.findByName(name));
        } else {
            categories = categoryService.findAll(pageable).getContent();
        }
        return categories;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Category getCategoryById(@PathVariable("id") Long id) {
        return categoryService.findOne(id);
    }

    @GetMapping(value = "/{id}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Post> getPostsByCategoryId(@PathVariable("id") Long id, Pageable pageable) {
        Category category = categoryService.findOne(id);
        return postService.findByCategory(category, pageable).getContent();
    }
}
