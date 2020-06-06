package controllers;

import model.Author;
import model.Category;
import model.Post;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.GenericService;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/blog")
public class PostController {
    private final GenericService<Post> postService;

    private final GenericService<Author> authorService;

    private final GenericService<Category> tagService;

    @Autowired
    public PostController(GenericService<Post> postService,
                          GenericService<Author> authorService,
                          GenericService<Category> tagService) {
        this.postService = postService;
        this.authorService = authorService;
        this.tagService = tagService;
    }

    @ModelAttribute(name = "tagList")
    public List<Category> tagList() {
        return tagService.findAll();
    }

    @ModelAttribute(name = "datePostList")
    public List<String> datePostList() {
        List<String> datePostList = new LinkedList<>();
        List<Post> postList = postService.findAll();
        for (Post post : postList) {
            LocalDate datePost = post.getCreateTime().toLocalDateTime().toLocalDate();
            Locale locale = new Locale("vi", "VN");
            Month month = datePost.getMonth();
            String monthDisplay = WordUtils.capitalizeFully(month.getDisplayName(TextStyle.FULL, locale));
            datePostList.add(monthDisplay);
        }
        return datePostList;
    }

    @ModelAttribute(name = "newDatePostList")
    public List<LocalDate> newDatePostList() {
        List<LocalDate> datePostList = new LinkedList<>();
        List<Post> postList = postService.findAll();
        for (Post post : postList) {
            datePostList.add(post.getCreateTime().toLocalDateTime().toLocalDate());
        }
        return datePostList;
    }

    @GetMapping("/{id}")
    public ModelAndView modelAndView(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Post postSelected = postService.findOne(id);
        Author authorOfSelectedPost = authorService.findOne(postSelected.getAuthor().getId());
        modelAndView.addObject("postSelected", postSelected);
        modelAndView.addObject("authorOfSelectedPost", authorOfSelectedPost);
        return modelAndView;
    }
}
