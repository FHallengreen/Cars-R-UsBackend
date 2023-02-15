package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    boolean existsByCarAndRentalDate(Car car, LocalDateTime rentalDate);

    @Query("SELECT r FROM Reservation r JOIN r.member m WHERE m.username = :username")
    List<Reservation> findByMemberUsername(@Param("username") String username);
}
