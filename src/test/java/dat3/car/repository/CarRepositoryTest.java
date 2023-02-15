package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
void setUp() {
        Member m1 = new Member("member1", "memb1@a.dk", "1234", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        Member m2 = new Member("member2", "aaa@dd.dk", "4321", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");

        Car car1 = new Car("Volvo", "CX90", 895, 695);
        Car car2 = new Car("BMW", "i330", 795, 645);
        Car car3 = new Car("VW", "ID4", 695, 595);
    carRepository.save(car1);
    carRepository.save(car2);
    carRepository.save(car3);
    memberRepository.save(m1);
    memberRepository.save(m2);

    }

    @Test
    void findAveragePricePrDayForAllCars() {
        int result = carRepository.findAveragePricePrDayForAllCars();
        assertEquals(795, result);
    }

    @Test
    void findCarsThatAreNotReserved() {
        List<Car> cars = carRepository.findCarsThatAreNotReserved();
        assertEquals(3, cars.size());
    }

    @Test
    void findCarByBrandAndModel() {
        List<Car> cars = carRepository.findAllCarByBrandAndModel("BMW", "i330");
        assertEquals(1, cars.size());
    }
}