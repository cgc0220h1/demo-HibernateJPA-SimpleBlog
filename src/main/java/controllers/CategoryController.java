package controllers;

import model.Category;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import service.post.PostService;
import util.PostUtil;


@Controller
@RequestMapping("/category")
public class CategoryController {
    private final PostService postService;
    private Category category;
    private String categoryId;

    @Autowired
    public CategoryController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/*/page/{pageNum}")
    public ModelAndView showPage(@PathVariable("pageNum") int pageName) {
        Pageable pageRequest = PageRequest.of(pageName - 1, 6, Sort.by("createTime").descending());
        Page<Post> postPage = postService.findByCategory(category, pageRequest);
        PostUtil.summaryPost(postPage, 36);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("postList", postPage);
        modelAndView.addObject("headerTitle", "Bài viết thuộc thể loại " + category.getName());
        modelAndView.addObject("numberOfPage", postPage.getTotalPages());
        modelAndView.addObject("controller", "category" + "/" + categoryId);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public RedirectView findCategory(@PathVariable("id") Category category) {
        this.category = category;
        this.categoryId = String.valueOf(category.getId());
        return new RedirectView("/category/" + categoryId + "/page/1");
    }
}
