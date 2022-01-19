package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 서비스 클래스는 비즈니스 처리와 관련된 메소드, 이름 사용해야함.

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // DI (Dependency Injection)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입 (이름중복 x) -> Id 반환
    public Long join(Member member){
    /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        // 반환값이 null일 가능성이 있으면 Optional로 감싸기!!

        result.ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    */
        validateDuplicateMember(member); // 이름 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // -> 결과가 Optional<Member>
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원조회
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    // 회원 검색
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
