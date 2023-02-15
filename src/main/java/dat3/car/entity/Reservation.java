package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "username")
    private Member member;
    @CreationTimestamp
    private LocalDate reservationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;


    public Reservation(Car car, Member member, LocalDate rentalDate) {
        this.car = car;
        this.member = member;
        this.rentalDate = rentalDate;
    }

}
