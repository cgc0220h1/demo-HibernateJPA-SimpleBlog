package app.blog.services.category;

import app.blog.model.Category;
import app.blog.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService extends GenericService<Category> {
    Category findByName(String name);
}
