package hello.hellospring.service;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 수동으로 SpringBean에 등록
@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();

        // jdbc에서 데이터 받아옴
        // DI를 사용하여 코드를 수정하지 않고 설정만으로 구현 클래스 변경 가능
       return new JdbcMemberRepository(dataSource);
    }

    // 1. memberService, memberRepository 를 SpringBean에 등록함
    // 2. 등록된 memberRepository를 MemberService에 넣어줌
    // => 데이터베이스 연결할 때 return값만 변경하면 되기때문에 수정이 용이함
}
