package controllers;

import model.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLClassLoader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class BlogController {
    private static List<Blog> blogList;
    private static long autoId = 0;

//    static {
//        blogList = new ArrayList<>();
//        blogList.add(new Blog(autoId++,
//                "Nguyến Văn Đức",
//                "Mạng sập chán vl",
//                "Ngày Lành Tháng tốt không phải lá lốt thì cũng là lá khoai",
//                new Timestamp(System.currentTimeMillis())));
//        blogList.add(new Blog(autoId++,
//                "Nguyễn Văn Nam",
//                "Chill đi bạn ei",
//                "một cộng một bằng hai, hai cộng hai bằng năm",
//                Timestamp.valueOf("2018-04-20 04:20:00")));
//    }

    @GetMapping()
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("blogList", blogList);
        return modelAndView;
    }
}
