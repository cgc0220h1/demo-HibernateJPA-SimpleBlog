package controllers;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import service.post.PostService;
import util.PostUtil;

import java.sql.Timestamp;

@Controller
@RequestMapping("/date")
public class DateController {
    private final PostService postService;
    private Timestamp[] range;
    private String rangeStr;

    @Autowired
    public DateController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/*/page/{pageNum}")
    public ModelAndView showPage(@PathVariable("pageNum") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("public/index");
        Pageable pageRequest = PageRequest.of(pageNum - 1, 6, Sort.by("createTime").descending());
        Page<Post> postList = postService.findInTimeRange(range[0], range[1], pageRequest);
        PostUtil.summaryPost(postList, 36);

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
        modelAndView.addObject("numberOfPage", postList.getTotalPages());
        modelAndView.addObject("postList", postList);
        modelAndView.addObject("controller", "date" + "/" + rangeStr);
        return modelAndView;
    }

    @GetMapping("/{range}")
    public RedirectView showPostByMonthAndYear(@PathVariable("range") Timestamp[] range,
                                               @PathVariable("range") String rangeStr) {
        this.range = range;
        this.rangeStr = rangeStr;
        return new RedirectView("/date/" + this.rangeStr + "/page/1");
    }
}
