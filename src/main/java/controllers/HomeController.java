package controllers;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import service.post.PostService;
import util.PostUtil;

@Controller
@RequestMapping({"/homepage", "/"})
public class HomeController {
    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public RedirectView showHome() {
        return new RedirectView("/homepage/page/1");
    }

    @GetMapping("/page/{pageNum}")
    public ModelAndView showPage(@PathVariable("pageNum") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("index");
        Pageable pageable = PageRequest.of(pageNum - 1, 6, Sort.by("createTime").descending());
        Page<Post> postList = postService.findAll(pageable);
        PostUtil.summaryPost(postList, 36);
        modelAndView.addObject("headerTitle", "Bài viết gần đây");
        modelAndView.addObject("postList", postList);
        modelAndView.addObject("numberOfPage", postList.getTotalPages());
        modelAndView.addObject("controller", "homepage");
        return modelAndView;
    }
}
