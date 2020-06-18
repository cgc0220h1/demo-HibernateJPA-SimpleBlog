package app.blog.controllers;

import app.blog.model.Category;
import app.blog.model.Comment;
import app.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import app.blog.services.category.CategoryService;
import app.blog.services.comment.CommentService;
import app.blog.services.post.PostService;
import app.blog.util.PostUtil;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

@Controller
@RequestMapping("/post")
public class PostController {

    private final MessageSource messageSource;

    private final PostService postService;

    private final CategoryService categoryService;

    private final CommentService commentService;

    @Autowired
    public PostController(MessageSource messageSource, PostService postService, CategoryService categoryService, CommentService commentService) {
        this.messageSource = messageSource;
        this.postService = postService;
        this.categoryService = categoryService;
        this.commentService = commentService;
    }

    @ModelAttribute(name = "categoryList")
    public List<Category> tagList() {
        return categoryService.findAll();
    }

    @ModelAttribute(name = "datePostMap")
    public Map<Year, Set<Month>> datePostMap() {
        Map<Year, Set<Month>> dateMap = new HashMap<>();
        List<Post> postList = postService.findAll(Sort.by("createTime").ascending());
        for (Post post : postList) {
            LocalDate postDate = post.getCreateTime().toLocalDateTime().toLocalDate();
            Year postYear = Year.of(postDate.getYear());
            Month postMonth = postDate.getMonth();
            dateMap.computeIfAbsent(postYear, k -> new LinkedHashSet<>());
            dateMap.get(postYear).add(postMonth);
        }
        return dateMap;
    }

    @GetMapping()
    public ModelAndView showPage(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Post> postPage = postService.findAll(pageable);
        PostUtil.summaryPost(postPage, 36);
        modelAndView.addObject("headerTitle", messageSource.getMessage("wall.title.recent", null, LocaleContextHolder.getLocale()));
        modelAndView.addObject("postPage", postPage);
        modelAndView.addObject("controller", "post");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView modelAndView(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Post postSelected = postService.findOne(id);
        List<Comment> comments = commentService.findCommentsByPost(postSelected, Sort.by("createTime").descending());
        if (comments == null) {
            comments = new LinkedList<>();
        }
        modelAndView.addObject("postSelected", postSelected);
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public RedirectView submitComment(@RequestParam("editor") String editor,
                                      @PathVariable("id") Long id) {
        System.out.println(editor);
        Post post = postService.findOne(id);
        post.setContent(editor);
        postService.save(post);
        return new RedirectView("/post/" + id);
    }

}
