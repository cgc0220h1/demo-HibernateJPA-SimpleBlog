package controllers;

import model.Author;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IService;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final IService<Post> postService;

    private final IService<Author> authorService;

    @Autowired
    public BlogController(IService<Post> postService, IService<Author> authorService) {
        this.postService = postService;
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ModelAndView modelAndView(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Post postSelected = postService.findOne(id);
        Author authorOfSelectedPost = authorService.findOne(postSelected.getAuthor().getId());
        modelAndView.addObject("postSelected", postSelected);
        modelAndView.addObject("authorOfSelectedPost", authorOfSelectedPost);
        return modelAndView;
    }
}
