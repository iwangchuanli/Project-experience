package com.wangcl.love;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class loveController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }
    @RequestMapping("/")
    public ModelAndView view(){
        return new ModelAndView("index");
    }
}
