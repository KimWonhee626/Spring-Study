package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    // data저장소 없다는 가정하게 store에 저장하여 관리
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // key값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // ID 세팅
        store.put(member.getId(), member); // store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null일때 Optional로 감싸서 반환함
        return Optional.ofNullable((store.get(id)));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store 비움
    public void clearStore(){
        store.clear();
    }
}
