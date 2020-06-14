package service.category;

import model.Category;
import service.GenericService;

public interface CategoryService extends GenericService<Category> {
    Category findByName(String name);
}
