package config.security;

import model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import services.author.AuthorService;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailsServiceImp implements UserDetailsService {
    private final AuthorService authorService;

    @Autowired
    public UserDetailsServiceImp(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author authorLogin = authorService.findByUserName(username);

        if (authorLogin == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(authorLogin.getUsername())));

        return new User(
                authorLogin.getUsername(),
                authorLogin.getPassword(),
                authorities
        );
    }
}
