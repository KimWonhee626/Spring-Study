package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스로 스프링 빈 자동등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member where m.name = ? 이 자동으로 됨 (메서드 이름을 통해서)
    @Override
    Optional<Member> findByName(String name);
}
