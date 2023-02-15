package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
    }

//    @Test
    public void testCreateReservationCarAlreadyReserved() {
        // Create test data with a car that is already reserved
        Member m1 = new Member("member1", "memb1@a.dk", "1234", "Kurt",
                "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        Car car1 = new Car("Volvo", "CX90", 895, 695);
        carRepository.save(car1);
        memberRepository.save(m1);

        LocalDateTime rentalDate = LocalDateTime.of(2023, 2, 15, 10, 0);

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setCar(car1);
        reservationRequest.setMember(m1);
        reservationRequest.setRentalDate(rentalDate);

        // Creates another Rreservation with the same car and rental date
        Reservation existingReservation = new Reservation();
        existingReservation.setCar(car1);
        existingReservation.setMember(m1);

        existingReservation.setRentalDate(LocalDateTime.now());

        reservationRepository.save(existingReservation);

        // expecting an IllegalArgumentException
        reservationService.createReservation(reservationRequest);
    }
}