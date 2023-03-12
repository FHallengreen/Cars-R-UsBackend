package dat3.car.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String addMember(MemberRequest memberRequest) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();

        if (memberRepository.existsById(memberRequest.getUsername())) {
            response.put("error", "Member with this ID already exists");
            return objectMapper.writeValueAsString(response);
        }

        if (memberRepository.existsByEmail(memberRequest.getEmail())) {
            response.put("error", "Member with this email already exists");
            return objectMapper.writeValueAsString(response);
        }

        Member newMember = MemberRequest.getMemberEntity(memberRequest);
        newMember = memberRepository.save(newMember);

        response.put("success", true);
        response.put("message", "Member created successfully");
        response.put("data", newMember);

        return objectMapper.writeValueAsString(response);
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();

/*        List<MemberResponse> memberResponses = new ArrayList<>();
        for (Member m : members){
            MemberResponse mr = new MemberResponse(m,includeAll);
            memberResponses.add(mr);
        }*/

        return members.stream().map(m -> new MemberResponse(m, false, true)).toList();

    }

    public MemberResponse getMemberById(String username) throws Exception {
        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        return new MemberResponse(member, true, true);
    }

    public void deleteMemberByUsername(String username) {
        memberRepository.deleteById(username);
    }

    public void setRankingForUser(String username, int value) {
        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        member.setRanking(value);
        memberRepository.save(member);
    }

    public ResponseEntity<Boolean> editMember(MemberRequest body, String username) {
        Member member = memberRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        member.setFirstName(body.getFirstName());
        member.setLastName(body.getLastName());
        member.setEmail(body.getEmail());
        member.setZip(body.getZip());
        memberRepository.save(member);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public List<Member> membersWithReservation() {
        return memberRepository.membersWithReservation();
    }
}
