package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DataJpaTest
class CarServiceTest {

    @Autowired
    private CarRepository carRepository;

    private CarService carService;

    boolean dataIsReady = false;
    @BeforeEach
    void setUp() {
        if (!dataIsReady) {  //Explain this
            carRepository.save(new Car("test", "test", 100, 100));
            carRepository.save(new Car("test2", "test2", 200, 200));
            carRepository.save(new Car("test3", "test3", 300, 300));
            dataIsReady = true;
            carService = new CarService(carRepository); //Real DB is mocked away with H2
        }
    }

    @Test
    void addCar() throws Exception {
        Car car = new Car("test3","Test3",10,10);
        //If Id was generated by the server, for example as for car you would need to set the id here
        carRepository.save(car);

        CarRequest request = new CarRequest(car);
        CarResponse response = carService.addCar(request);

        //Quick way to get a MemberRequest (remember eventually values come via a incoming JSON object)
        assertEquals(4,response.getCar_id());
    }

    @Test
    void getCars() {
        List<CarResponse> cars = carService.getCars(true);
        assertEquals(3,carService.getCars(true).size());
        assertInstanceOf(CarResponse.class,cars.get(0));
    }
}