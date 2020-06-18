package app.blog.services.author;

import app.blog.model.Author;
import app.blog.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService extends GenericService<Author> {
    Author findByUserName(String username);
}
