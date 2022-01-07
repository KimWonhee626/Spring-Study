package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 컨트롤러 생성시 스프링 컨테이너의 memberService 객체를 연결시켜줌(DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new") // post:보통 데이터 등록할때 사용 (action tag 통해 호출)
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입 끝나면 home으로 감
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        // 멤버 list를 model에 담아서 넘김
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
