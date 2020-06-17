package services.author;

import model.Author;
import services.GenericService;

public interface AuthorService extends GenericService<Author> {
    Author findByUserName(String username);
}
