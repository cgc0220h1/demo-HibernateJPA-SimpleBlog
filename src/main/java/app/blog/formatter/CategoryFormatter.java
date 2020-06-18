package app.blog.formatter;

import app.blog.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import app.blog.services.category.CategoryService;

import java.util.Locale;

@Component
public class CategoryFormatter implements Formatter<Category> {
    private final CategoryService categoryService;

    @Autowired
    public CategoryFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category parse(String text, Locale locale) {
        return categoryService.findOne(Long.parseLong(text));
    }

    @Override
    public String print(Category category, Locale locale) {
        return category.getName();
    }
}
