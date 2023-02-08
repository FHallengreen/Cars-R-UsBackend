package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceTest {

    @Autowired
    private CarRepository carRepository;

    private CarService carService;

    boolean dataIsReady = false;
    @BeforeEach
    void setUp() {
        if (!dataIsReady) {  //Explain this
            carRepository.save(new Car("test", "test", 200, 300));
            dataIsReady = true;
            carService = new CarService(carRepository); //Real DB is mocked away with H2
        }
    }

    @Test
    void addCar() {
    }

    @Test
    void getCars() {
    }
}