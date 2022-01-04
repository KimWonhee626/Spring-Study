package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController  {

    @GetMapping("hello") // url에 '/hello'치면 이 컨트롤러 불러짐
    public String hello(Model model){

        // 키는 data, 값은 hello!!
        model.addAttribute("data", "hello!!");
        return "hello"; // resource\templates\hello.html 실행시킴
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        // 외부에서 파라미터 받음
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // 문자 반환 => 그대로 html화면에 써짐
    }

    @GetMapping("hello-api") // 객체반환(view없읍)
    @ResponseBody //
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체 반환 => json으로 반환!!!(default)
//        @ResponseBody 를 사용
//        HTTP의 BODY에 문자 내용을 직접 반환
//        viewResolver 대신에 HttpMessageConverter 가 동작
//        기본 문자처리: StringHttpMessageConverter
//        기본 객체처리: MappingJackson2HttpMessageConverter
//        byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

}
}
