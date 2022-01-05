package hello.hellospring.service;

import hello.hellospring.domain.Member;
import static org.assertj.core.api.Assertions.*;

import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given (무슨 데이터 기반?)
        Member member = new Member();
        member.setName("wonhee");

        // when (뭘 검증하는지)
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("wonhee");

        Member member2 = new Member();
        member2.setName("wonhee");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()
        -> memberService.join(member2));

        // then
        // 중복회원 있을 때
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*       try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){ // 예외 발생(ㅈ
            Assertions.assertThat(e.getMessage())
                    .isEqualTo("이미 존재하는 회원입니다.");
        }
*/
    }

    @Test
    void findMember() {

    }

    @Test
    void findOne() {
    }
}