package controllers;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IService;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final IService<Post> postService;

    @Autowired
    public BlogController(IService<Post> postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ModelAndView modelAndView(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Post post = postService.findOnePost(id);
        modelAndView.addObject("post", post);
        return modelAndView;
    }
}
