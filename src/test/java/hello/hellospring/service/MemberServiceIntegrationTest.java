package hello.hellospring.service;

import com.sun.jdi.event.ExceptionEvent;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 테스트때 필요한 어노테이션
@SpringBootTest
@Transactional // test 끝나면 롤백해줌(메서드마다 적용됨)
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given (무슨 데이터 기반?)
        Member member = new Member();
        member.setName("wonhee1");

        // when (뭘 검증하는지)
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("wonhee");

        Member member2 = new Member();
        member2.setName("wonhee");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        // then
        // 중복회원 있을 때
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}