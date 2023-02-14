package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;


@DataJpaTest
class ReservationServiceTest {

/*    @Autowired
    ReservationService reservationService;

    public ReservationServiceTest(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void makeReservation() {
        Member m2 = new Member("member2", "aaa@dd.dk", "4321", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        Car car1 = new Car("Volvo", "CX90", 895, 695);


        ReservationRequest request = new ReservationRequest();
        ReservationResponse response = reservationService.makeReservation(request);
        assertEquals(1, response.getId());
    }*/
}