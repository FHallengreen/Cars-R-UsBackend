package dat3.car.repository;

import dat3.car.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    boolean existsByEmail(String email);

    List<Member> findMemberByZip(String zip);

//    SELECT * FROM user_with_roles m JOIN reservation r ON m.username = r.username;
    @Query("SELECT m FROM Member m JOIN m.reservations r")
    List<Member> membersWithReservation();

}
