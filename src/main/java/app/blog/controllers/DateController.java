package app.blog.controllers;

import app.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import app.blog.services.post.PostService;
import app.blog.util.PostUtil;

import java.sql.Timestamp;

@Controller
@RequestMapping("/date")
public class DateController {

    private final MessageSource messageSource;

    private final PostService postService;

    @Autowired
    public DateController(MessageSource messageSource, PostService postService) {
        this.messageSource = messageSource;
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
            headerTitle = messageSource.getMessage("wall.title.date.month", null, LocaleContextHolder.getLocale())
                    + range[0].toLocalDateTime().getMonthValue()
                    + messageSource.getMessage("wall.title.date.year", null, LocaleContextHolder.getLocale())
                    + range[0].toLocalDateTime().getYear();
        } else {
            headerTitle = messageSource.getMessage("wall.title.year", null, LocaleContextHolder.getLocale())
                    + range[0].toLocalDateTime().getYear();
        }

        modelAndView.addObject("headerTitle", headerTitle);
        modelAndView.addObject("postPage", postPage);
        modelAndView.addObject("controller", "date" + "/" + rangeStr);
        return modelAndView;
    }
}
