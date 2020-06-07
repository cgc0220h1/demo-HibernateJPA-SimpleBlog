package controllers;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.GenericService;
import util.PostUtil;

@Controller
@RequestMapping("/homepage")
public class HomeController {
    private final GenericService<Post> postService;

    @Autowired
    public HomeController(GenericService<Post> postService) {
        this.postService = postService;
    }

    @GetMapping()
    public ModelAndView showHome() {
        return showPage(1);
    }

    @GetMapping("/page{id}")
    public ModelAndView showPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Post> postList = postService.findAll(id - 1, 6);
        Page<Post> filterPost = PostUtil.summaryPost(postList,36);
        modelAndView.addObject("numberOfPage", filterPost.getTotalPages());
        modelAndView.addObject("postList", filterPost);
        modelAndView.addObject("headerTitle", "Bài viết gần đây");
        return modelAndView;
    }
}
