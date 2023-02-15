package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("SELECT c FROM Car c WHERE c.brand = :brand AND c.model = :model")
    List<Car> findAllCarByBrandAndModel(String brand, String model);

    @Query("SELECT AVG(c.pricePrDay) FROM Car c")
    int findAveragePricePrDayForAllCars();

    @Query("SELECT c.bestDiscount FROM Car c ORDER BY c.bestDiscount DESC")
    int findBestDiscount();

    @Query("SELECT c FROM Car c WHERE c NOT IN (SELECT r.car FROM Reservation r)")
    List<Car> findCarsThatAreNotReserved();

}
