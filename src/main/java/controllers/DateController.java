package controllers;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.post.PostService;
import util.PostUtil;

import java.sql.Timestamp;

@Controller
@RequestMapping("/date")
public class DateController {
    private final PostService postService;

    @Autowired
    public DateController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{range}")
    public ModelAndView showPage(@PathVariable("range") Timestamp[] range,
                                 @PathVariable("range") String rangeStr,
                                 Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Post> postPage = postService.findInTimeRange(range[0], range[1], pageable);
        PostUtil.summaryPost(postPage, 36);

        String headerTitle;
        if (range[0].toLocalDateTime().getMonth() == range[1].toLocalDateTime().getMonth()) {
            headerTitle = "Bài viết trong tháng "
                    + range[0].toLocalDateTime().getMonthValue()
                    + " năm "
                    + range[0].toLocalDateTime().getYear();
        } else {
            headerTitle = "Bài viết trong năm "
                    + range[0].toLocalDateTime().getYear();
        }

        modelAndView.addObject("headerTitle", headerTitle);
        modelAndView.addObject("postPage", postPage);
        modelAndView.addObject("controller", "date" + "/" + rangeStr);
        return modelAndView;
    }
}
