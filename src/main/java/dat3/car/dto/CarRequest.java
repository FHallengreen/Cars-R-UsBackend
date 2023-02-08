package dat3.car.dto;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {

    private int car_id;
    private String brand;
    private String model;
    private double pricePrDay;
    private int bestDiscount;
/*    private LocalDateTime created;
    private LocalDateTime lastEdited;*/

    public static Car getCarEntity(CarRequest c) {
        return new Car(c.car_id, c.brand, c.model, c.pricePrDay, c.bestDiscount);
    }

    public CarRequest(Car c) {
        this.car_id = c.getCar_id();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
    }
}
