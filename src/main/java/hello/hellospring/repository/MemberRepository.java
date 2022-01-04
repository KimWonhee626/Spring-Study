package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // Optional : null 반환시 Optional로 감싸서 반환
    List<Member> findAll();
}

