package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

    private int car_id;
    private String brand;
    private String model;
    private double pricePrDay;
    private int bestDiscount;
    private LocalDateTime created;
    private LocalDateTime lastEdited;

    public CarResponse(Car c, boolean includeAll) {
        this.car_id = c.getCar_id();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
        if (includeAll) {
            this.created = c.getCreated();
            this.lastEdited = c.getLastEdited();
        }
    }


}
