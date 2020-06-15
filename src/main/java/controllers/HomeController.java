package controllers;

import model.Author;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import service.author.AuthorService;
import service.post.PostService;
import util.PostUtil;

@Controller
@RequestMapping({"/homepage", "/", "/admin"})
public class HomeController {

    private final MessageSource messageSource;

    private final PostService postService;

    private final AuthorService authorService;

    @Autowired
    public HomeController(MessageSource messageSource, PostService postService, AuthorService authorService) {
        this.messageSource = messageSource;
        this.postService = postService;
        this.authorService = authorService;
    }

    @GetMapping
    public RedirectView redirectView() {
        return new RedirectView("/post");
    }

    @GetMapping("/search")
    public ModelAndView showPage(@RequestParam("content") String content, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Post> postPage = postService.findByContent(content, pageable);
        PostUtil.summaryPost(postPage, 36);
        String headerTitle = messageSource.getMessage("wall.title.search.name", null, LocaleContextHolder.getLocale())
                + content
                + " ("
                + postPage.getTotalElements()
                + messageSource.getMessage("wall.title.search.result", null, LocaleContextHolder.getLocale())
                + ")";
        modelAndView.addObject("headerTitle", headerTitle);
        modelAndView.addObject("postPage", postPage);
        modelAndView.addObject("controller", "post");
        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView showAboutPage(@PageableDefault(size = 6, sort = "name", direction = Sort.Direction.ASC)
                                              Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("about");
        Page<Author> authorPage = authorService.findAll(pageable);
        modelAndView.addObject("authorPage", authorPage);
        return modelAndView;
    }
}
