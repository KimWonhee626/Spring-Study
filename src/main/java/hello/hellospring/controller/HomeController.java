package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // static보다 우선순위 높아서 index.html 호출 안됨

    @GetMapping("/")
    public String home(){
        return "home"; // home.html 호출
    }
}
