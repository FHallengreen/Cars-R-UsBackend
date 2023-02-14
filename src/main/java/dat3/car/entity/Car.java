package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int car_id;

    @Column(name = "car_brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "car_model", nullable = false, length = 60)
    private String model;

    @Column(name = "rental_price_day")
    private double pricePrDay;

    @Column(name = "max_discount")
    private int bestDiscount;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Car(int car_id, String brand, String model, double pricePrDay, int bestDiscount) {
        this.car_id = car_id;
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }

    public Car(String brand, String model, double pricePrDay, int bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }

    @Override
    public String toString() {
        return "Car{" +
                "car_id=" + car_id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", pricePrDay=" + pricePrDay +
                ", bestDiscount=" + bestDiscount +
                ", created=" + created +
                ", lastEdited=" + lastEdited +
                '}';
    }
}
