package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 수동으로 SpringBean에 등록
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    // 1. memberService, memberRepository 를 SpringBean에 등록함
    // 2. 등록된 memberRepository를 MemberService에 넣어줌
    // => 데이터베이스 연결할 때 return값만 변경하면 되기때문에 수정이 용이함
}
