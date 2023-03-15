package dat3.car.config;

import dat3.car.dto.ReservationRequest;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.car.service.ReservationService;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DeveloperData implements ApplicationRunner {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserWithRolesRepository userWithRolesRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    ReservationService reservationService = new ReservationService(reservationRepository, carRepository, memberRepository, userWithRolesRepository);


    final String passwordUsedByAll = "test12";

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Member m1 = new Member("member1", "memb1@a.dk", "1234", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        Member m2 = new Member("member2", "aaa@dd.dk", "4321", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");

        Member member1 = new Member("john", "john@example.com", "1234", "John", "Doe", "123 Main St", "Anytown", "12345");
        Member member2 = new Member("jane", "jane@example.com", "5678", "Jane", "Doe", "456 Oak St", "Anytown", "12345");
        Member member3 = new Member("bob", "bob@example.com", "9012", "Bob", "Smith", "789 Maple St", "Anytown", "12345");
        Member member4 = new Member("susan", "susan@example.com", "3456", "Susan", "Smith", "1011 Pine St", "Anytown", "12345");
        Member member5 = new Member("jim", "jim@example.com", "7890", "Jim", "Jones", "1213 Cedar St", "Anytown", "12345");
        Member member6 = new Member("kate", "kate@example.com", "2345", "Kate", "Johnson", "1415 Elm St", "Anytown", "12345");
        Member member7 = new Member("mark", "mark@example.com", "6789", "Mark", "Brown", "1617 Walnut St", "Anytown", "12345");
        Member member8 = new Member("emily", "emily@example.com", "0123", "Emily", "Davis", "1819 Oak St", "Anytown", "12345");

        Car car1 = new Car("Toyota", "Corolla", 800, 700);
        Car car2 = new Car("Honda", "Civic", 750, 650);
        Car car3 = new Car("Ford", "Mustang", 1200, 1100);
        Car car4 = new Car("Chevrolet", "Camaro", 1100, 1000);
        Car car5 = new Car("BMW", "M3", 1500, 1400);
        Car car6 = new Car("Mercedes-Benz", "C-Class", 1000, 900);
        Car car7 = new Car("Audi", "A4", 900, 800);
        Car car8 = new Car("Lamborghini", "Aventador", 2500, 2400);
        Car car9 = new Car("Ferrari", "488", 2000, 1900);
        Car car10 = new Car("Porsche", "911", 1800, 1700);
        Car car11 = new Car("Tesla", "Model S", 1500, 1400);
        Car car12 = new Car("Nissan", "Leaf", 600, 500);
        Car car13 = new Car("Mazda", "CX-5", 850, 750);
        Car car14 = new Car("Volvo", "XC90", 1200, 1100);
        Car car15 = new Car("Kia", "Sorento", 700, 600);
        Car car16 = new Car("Hyundai", "Tucson", 600, 500);
        Car car17 = new Car("Jeep", "Grand Cherokee", 1000, 900);
        Car car18 = new Car("Subaru", "Outback", 800, 700);
        Car car19 = new Car("Land Rover", "Range Rover", 2000, 1900);

        m1.addRole(Role.ADMIN);
        m1.addRole(Role.USER);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);
        carRepository.save(car7);
        carRepository.save(car8);
        carRepository.save(car9);
        carRepository.save(car10);
        carRepository.save(car11);
        carRepository.save(car12);
        carRepository.save(car13);
        carRepository.save(car14);
        carRepository.save(car15);
        carRepository.save(car16);
        carRepository.save(car17);
        carRepository.save(car18);
        carRepository.save(car19);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
        memberRepository.save(member6);
        memberRepository.save(member7);
        memberRepository.save(member8);

        memberRepository.save(m1);
        memberRepository.save(m2);

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setCarId(1);
        reservationRequest.setMemberId("member1");
        reservationRequest.setRentalDate(LocalDate.now());

        reservationService.createReservation(reservationRequest);

        ReservationRequest reservationRequest2 = new ReservationRequest();
        reservationRequest2.setCarId(2);
        reservationRequest2.setMemberId("member2");
        reservationRequest2.setRentalDate(LocalDate.now());

        reservationService.createReservation(reservationRequest2);


        setupUserWithRoleUsers();
    }

    private void setupUserWithRoleUsers() {

        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }

}
