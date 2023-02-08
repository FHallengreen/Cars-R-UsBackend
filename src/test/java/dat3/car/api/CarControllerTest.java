package dat3.car.api;

import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import dat3.car.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarControllerTest {

    @Autowired
    CarRepository carRepository;

    CarService carService;

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
    void getCars() {
        List<CarResponse> carlist = carService.getCars(true);

        assertEquals(3, carlist.size());
    }

}