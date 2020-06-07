package controllers;

import model.Author;
import model.Category;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.GenericService;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

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

    @ModelAttribute(name = "datePostMap")
    public Map<Year, Set<Month>> datePostMap() {
        Map<Year, Set<Month>> dateMap = new HashMap<>();
        List<Post> postList = postService.findAll(Sort.by(Sort.Direction.ASC, "createTime"));

        for (Post post : postList) {
            LocalDate postDate = post.getCreateTime().toLocalDateTime().toLocalDate();
            Year postYear = Year.of(postDate.getYear());
            Month postMonth = postDate.getMonth();
            if (dateMap.get(postYear) == null) {
                dateMap.put(postYear, new LinkedHashSet<>());
            }
            dateMap.get(postYear).add(postMonth);
        }
        return dateMap;
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
