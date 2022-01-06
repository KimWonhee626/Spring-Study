package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 컨트롤러 생성시 스프링 컨테이너의 memberService 객체를 연결시켜줌(DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
