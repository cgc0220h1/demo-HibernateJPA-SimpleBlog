package controllers;

import model.Category;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.post.PostService;
import util.PostUtil;


@Controller
@RequestMapping("/category")
public class CategoryController {
    private final PostService postService;

    @Autowired
    public CategoryController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ModelAndView showPage(@PathVariable("id") Category category, Pageable pageable) {
        Page<Post> postPage = postService.findByCategory(category, pageable);
        PostUtil.summaryPost(postPage, 36);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("postPage", postPage);
        modelAndView.addObject("headerTitle", "Bài viết thuộc thể loại " + category.getName());
        modelAndView.addObject("controller", "category" + "/" + category.getId());
        return modelAndView;
    }
}
