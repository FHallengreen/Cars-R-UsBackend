package dat3.car.service;

import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
class MemberServiceMockitoTest {

    @Mock
    private MemberRepository memberRepository;

    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    void getMembers() {
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(new Member("member1", "memb1@a.dk",
                "1234", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800"),
                new Member("member2", "aaa@dd.dk", "4321", "Hanne", "Wonnegut",
                        "Lyngbyvej 2", "Lyngby", "2800")));

        List<MemberResponse> members = memberService.getMembers(true);
        assertEquals(2,memberService.getMembers(true).size());
        assertInstanceOf(MemberResponse.class,members.get(0));
    }
}