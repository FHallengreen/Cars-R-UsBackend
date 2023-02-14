package dat3.car.dto;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {

    private Member username;
    private Car carId;
    private LocalDateTime reservationDate;

    public ReservationRequest(Member username, Car carId, LocalDateTime reservationDate) {
        this.username = username;
        this.carId = carId;
        this.reservationDate = reservationDate;
    }



}
