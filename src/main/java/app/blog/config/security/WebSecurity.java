package app.blog.config.security;

import app.blog.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import app.blog.services.author.AuthorService;

@Component
public class WebSecurity {
    private final AuthorService authorService;

    @Autowired
    public WebSecurity(AuthorService authorService) {
        this.authorService = authorService;
    }

    public boolean checkUsername(Authentication authentication, String username) {
        Author authorLogin = authorService.findByUserName(authentication.getName());
        return authorLogin != null && authorLogin.getUsername().equals(username);
    }
}
