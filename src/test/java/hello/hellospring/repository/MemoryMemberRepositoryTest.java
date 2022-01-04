package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 하나의 메소드 끝날때마다 호출됨
            // => test는 순서와 상관 없이 설계해야해서
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("wonhee");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // System.out.println("result = " + (result==member));
        // Assertions.assertEquals(member,result);
        Assertions.assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("wonhee1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("wonhee2");
        repository.save(member2);

        Member result = repository.findByName("wonhee1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("wonhee1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("wonhee2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }
}
