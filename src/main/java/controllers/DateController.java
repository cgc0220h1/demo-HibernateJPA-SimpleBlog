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
@RequestMapping("/date")
public class DateController {
    private final GenericService<Post> postService;

    @Autowired
    public DateController(GenericService<Post> postService) {
        this.postService = postService;
    }

    @GetMapping
    public ModelAndView showPostByMonthAndYear(@RequestParam("range") Timestamp[] range) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Post> postList = postService.findInDateRange(range[0], range[1]);
        Page<Post> summaryPost = PostUtil.summaryPost(postList,36);
        modelAndView.addObject("numberOfPage", postList.getTotalPages());
        modelAndView.addObject("postList", summaryPost);

        if (range[0].toLocalDateTime().getMonth() == range[1].toLocalDateTime().getMonth()) {
            modelAndView.addObject("headerTitle",
                    "Bài viết trong tháng "
                            + range[0].toLocalDateTime().getMonthValue()
                            + " năm " + range[0].toLocalDateTime().getYear());
        } else {
            modelAndView.addObject("headerTitle", "Bài viết trong năm " +
                    range[0].toLocalDateTime().getYear());
        }

        return modelAndView;
    }
}
