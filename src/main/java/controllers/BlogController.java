package controllers;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IService;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class BlogController {
    private IService<Post> postService;

    @Autowired
    public BlogController(IService<Post> postService) {
        this.postService = postService;
    }

    @GetMapping()
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        int numberOfPage = postService.findPostPage(0, 6).getTotalPages();
        List<Post> postList = postService.findPostByPage(0, 6);
        List<Post> filterPost = new LinkedList<>();
        for (Post post : postList) {
            post.setContent(truncateAfterWords(36, post.getContent()));
            filterPost.add(post);
        }
        modelAndView.addObject("numberOfPage", numberOfPage);
        modelAndView.addObject("postList", filterPost);
        return modelAndView;
    }

    private String truncateAfterWords(int numberOfWords, String string) {
        final Pattern WB_PATTERN = Pattern.compile("(?<=\\w)\\b");
        if (string == null) return null;
        if (numberOfWords <= 0) return "";
        Matcher m = WB_PATTERN.matcher(string);
        for (int i = 0; i < numberOfWords && m.find(); i++) ;
        if (m.hitEnd())
            return string + " ... ";
        else
            return string.substring(0, m.end()) + " ... ";
    }
}
