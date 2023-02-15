package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Member member;
    private Car car;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime rentalDate;

    public ReservationRequest(Member member, Car car, LocalDateTime rentalDate) {
        this.member = member;
        this.car = car;
        this.rentalDate = rentalDate;
    }



}
