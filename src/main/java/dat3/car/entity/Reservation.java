package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @ManyToOne
    @JoinColumn(name = "username")
    private Member member;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime reservationDate;

    public Reservation(Car car, Member member, LocalDateTime reservationDate) {
        this.car = car;
        this.member = member;
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", car=" + car +
                ", member=" + member +
                ", reservationDate=" + reservationDate +
                '}';
    }

}
