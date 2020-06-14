package api;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesAPI {
    private final CategoryService categoryService;

    @Autowired
    public CategoriesAPI(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Category> getCategories(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.DESC) Pageable pageable) {
        return categoryService.findAll(pageable).getContent();
    }
}
