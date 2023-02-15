package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "username")
    private Member member;
    @CreationTimestamp
    private LocalDateTime reservationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime rentalDate;


    public Reservation(Car car, Member member, LocalDateTime rentalDate) {
        this.car = car;
        this.member = member;
        this.rentalDate = rentalDate;
    }

}
