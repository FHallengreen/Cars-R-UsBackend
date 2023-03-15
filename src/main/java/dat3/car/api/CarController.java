package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Car;
import dat3.car.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    List<CarResponse> getCars() {
        return carService.getCars(true);
    }

    @GetMapping(path = "/{brand}/{model}")
    List<Car> findCarsByBrandAndModel(@PathVariable String brand, @PathVariable String model) {
        return carService.findCarsByBrandAndModel(brand, model);
    }

    @GetMapping(path = "/average-price")
    String findAveragePricePrDayForAllCars() {
        int averagePrice = carService.findAveragePricePrDayForAllCars();
        return averagePrice + " kr. per day";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/{car_id}")
    CarResponse getCarById(@PathVariable int car_id) throws Exception {
        return carService.getCarById(car_id);
    }

    //ANONYM
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarResponse addCar(@RequestBody CarRequest body) {
    return carService.addCar(body);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/{car_id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int car_id) {
        return carService.editCar(body,car_id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/price_per_day/{car_id}/{value}")
    void setPricePerDay(@PathVariable int car_id, @PathVariable int value) {
        carService.setPricePerDayForCar(car_id,value);
    }

        @PatchMapping("/max_discount/{car_id}/{value}")
    void setMaxDiscount(@PathVariable int car_id, @PathVariable int value) {
        carService.setMaxDiscount(car_id,value);
    }



    // ADMIN
    @DeleteMapping("/{car_id}")
    void deleteCarByID(@PathVariable int car_id) {
    carService.deleteCarByCarId(car_id);
    }

}
