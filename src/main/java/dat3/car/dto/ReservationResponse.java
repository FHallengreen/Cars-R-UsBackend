package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.ReservationRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    private int reservationId;
    private String zip;
    private int carId;
    private String brand;
    private String model;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime rentalDate;


    public ReservationResponse(Reservation r) {
        this.reservationId = r.getId();
        this.zip = r.getMember().getZip();
        this.carId = r.getCar().getCar_id();
        this.brand = r.getCar().getBrand();
        this.model = r.getCar().getModel();
        this.rentalDate = r.getRentalDate();

    }

    @Override
    public String toString() {
        return "ReservationResponse(" +
                "id=" + reservationId +
                ", zip='" + zip + '\'' +
                ", carId=" + carId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", rentalDate=" + rentalDate +
                ')';
    }

}
