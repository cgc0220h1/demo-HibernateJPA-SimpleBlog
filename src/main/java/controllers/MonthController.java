package controllers;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.GenericService;
import util.PostUtil;

import java.sql.Timestamp;

@Controller
@RequestMapping("/month")
public class MonthController {
    private final GenericService<Post> postService;

    @Autowired
    public MonthController(GenericService<Post> postService) {
        this.postService = postService;
    }

    @GetMapping
    public ModelAndView testMethod(@RequestParam("range") Timestamp[] range) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Post> postList = postService.findInDateRange(range[0], range[1]);
        Page<Post> summaryPost = PostUtil.summaryPost(postList);
        modelAndView.addObject("numberOfPage", postList.getTotalPages());
        modelAndView.addObject("postList", summaryPost);
        modelAndView.addObject("headerTitle",
                "Bài viết trong tháng "
                        + range[0].toLocalDateTime().getMonthValue()
        + " năm " + range[0].toLocalDateTime().getYear());
        return modelAndView;
    }
}
