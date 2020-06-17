package config.security;

import model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import services.author.AuthorService;

@Component
public class WebSecurity {
    private final AuthorService authorService;

    @Autowired
    public WebSecurity(AuthorService authorService) {
        this.authorService = authorService;
    }

    public boolean checkAuthorId(Authentication authentication, String username) {
        Author authorLogin = authorService.findByUserName(authentication.getName());
        return authorLogin != null && authorLogin.getUsername().equals(username);
    }
}
