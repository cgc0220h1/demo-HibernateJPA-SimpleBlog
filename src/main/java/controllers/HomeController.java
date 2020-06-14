package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping({"/homepage", "/"})
public class HomeController {

    @GetMapping
    public RedirectView redirectView() {
        return new RedirectView("/post");
    }

}
