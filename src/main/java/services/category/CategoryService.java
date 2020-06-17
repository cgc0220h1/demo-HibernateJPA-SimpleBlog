package services.category;

import model.Category;
import services.GenericService;

public interface CategoryService extends GenericService<Category> {
    Category findByName(String name);
}
