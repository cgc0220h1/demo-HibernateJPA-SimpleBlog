package controllers;

import model.Author;
import model.Post;
import model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.GenericService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final GenericService<Post> postService;

    private final GenericService<Author> authorService;

    private final GenericService<Tag> tagService;

    @Autowired
    public BlogController(GenericService<Post> postService,
                          GenericService<Author> authorService,
                          GenericService<Tag> tagService) {
        this.postService = postService;
        this.authorService = authorService;
        this.tagService = tagService;
    }

    @ModelAttribute(name = "tagList")
    public List<Tag> tagList() {
        return tagService.findAll();
    }

    @ModelAttribute(name = "datePostList")
    public List<LocalDate> datePostList() {
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
