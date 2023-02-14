package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.ReservationRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    private int id;
    private String username;
    private String zip;
    private int carId;
    private String brand;
    private String model;
    private String reservationDate;


    public ReservationResponse(Reservation r) {
        this.id = r.getId();
        this.username = r.getMember().getUsername();
        this.zip = r.getMember().getZip();
        this.carId = r.getCar().getCar_id();
        this.brand = r.getCar().getBrand();
        this.model = r.getCar().getModel();
        this.reservationDate = r.getReservationDate().toString();

    }


}
