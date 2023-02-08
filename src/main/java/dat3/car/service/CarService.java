package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public CarResponse addCar(CarRequest carRequest) {

        Car newCar = CarRequest.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);
        return new CarResponse(newCar, false);
    }

    public List<CarResponse> getCars(boolean includeAll) {
        List<Car> cars = carRepository.findAll();

        return cars.stream().map(m -> new CarResponse(m, includeAll)).toList();

    }

    public CarResponse getCarById(int car_id) throws Exception {
        Car car = carRepository.findById(car_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        return new CarResponse(car, true);
    }

    public void deleteCarByCarId(int car_id) {
        carRepository.deleteById(car_id);
    }

    public void setPricePerDayForCar(int car_id, int value) {
        Car car = carRepository.findById(car_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        car.setPricePrDay(value);
        carRepository.save(car);
    }

    public void setMaxDiscount(int car_id, int value) {
        Car car = carRepository.findById(car_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        car.setBestDiscount(value);
        carRepository.save(car);
    }

    public ResponseEntity<Boolean> editCar(CarRequest body, int car_id) {
        Car car = carRepository.findById(car_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setBestDiscount(body.getBestDiscount());
        car.setPricePrDay(body.getPricePrDay());
        carRepository.save(car);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
