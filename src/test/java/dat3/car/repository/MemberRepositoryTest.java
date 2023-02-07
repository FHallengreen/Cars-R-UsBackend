package dat3.car.repository;

import dat3.car.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member m1 = new Member("member1", "memb1@a.dk", "1234", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");

       memberRepository.save(m1);

    }

    @Test
    void findMemberByZip() {
        List<Member> members = memberRepository.findMemberByZip("2800");
//        assertEquals(1,members.size());
        assertEquals("2800",members.get(0).getZip());
    }
}