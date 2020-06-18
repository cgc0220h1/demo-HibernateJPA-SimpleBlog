package app.blog.controllers;

import app.blog.model.Author;
import app.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import app.blog.services.author.AuthorService;
import app.blog.services.post.PostService;

@Controller
@RequestMapping("/{username}")
public class AuthorController {
    private final AuthorService authorService;

    private final PostService postService;

    @Autowired
    public AuthorController(AuthorService authorService, PostService postService) {
        this.authorService = authorService;
        this.postService = postService;
    }

    @ModelAttribute("author")
    public Author author(@PathVariable("username") String username) {
        return authorService.findByUserName(username);
    }

    @GetMapping({"", "/profile"})
    public ModelAndView showPost(@ModelAttribute("author") Author author, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Post> postPage = postService.findByAuthor(author, pageable);
        modelAndView.addObject("postPage", postPage);
        modelAndView.addObject("headerTitle", author.getName());
        modelAndView.addObject("authorAccess", author);
        System.out.println(author.getUsername());
        return modelAndView;
    }

//    @GetMapping("/profile")
//    public ModelAndView showProfile() {
//        ModelAndView modelAndView = new ModelAndView("author");
//        modelAndView.addObject("author");
//        return modelAndView;
//    }
}
