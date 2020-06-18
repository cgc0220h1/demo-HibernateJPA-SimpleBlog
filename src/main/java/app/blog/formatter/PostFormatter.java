package app.blog.formatter;

import app.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import app.blog.services.post.PostService;

import java.util.Locale;

@Component
public class PostFormatter implements Formatter<Post> {
    private final PostService postService;

    @Autowired
    public PostFormatter(PostService postService) {
        this.postService = postService;
    }

    @Override
    public Post parse(String text, Locale locale) {
        return postService.findOne(Long.parseLong(text));
    }

    @Override
    public String print(Post object, Locale locale) {
        return null;
    }
}
