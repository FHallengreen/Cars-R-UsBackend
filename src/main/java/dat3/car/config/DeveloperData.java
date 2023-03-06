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

        Car car1 = new Car("Volvo", "CX90", 895, 695);
        Car car2 = new Car("BMW", "i330", 795, 645);
        Car car3 = new Car("VW", "ID4", 695, 595);

        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

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
        reservationRequest2.setRentalDate(LocalDate.of(2024,3,5));
        reservationService.createReservation(reservationRequest2);

        Member m3 = new Member("member3", "bbb@cc.dk", "5678", "Sarah", "Smith", "Nørrebrogade 10", "Copenhagen", "2200");
        Member m4 = new Member("member4", "xyz@abc.dk", "8765", "John", "Doe", "Amagerbrogade 50", "Copenhagen", "2300");

        Car car4 = new Car("Tesla", "Model S", 995, 795);
        Car car5 = new Car("Audi", "Q5", 745, 595);
        Car car6 = new Car("Mercedes-Benz", "C-Class", 895, 695);

        carRepository.save(car4);
        carRepository.save(car5);
        carRepository.save(car6);

        memberRepository.save(m3);
        memberRepository.save(m4);

        ReservationRequest reservationRequest3 = new ReservationRequest();
        reservationRequest3.setCarId(3);
        reservationRequest3.setMemberId("member3");
        reservationRequest3.setRentalDate(LocalDate.of(2023, 3, 8));
        reservationService.createReservation(reservationRequest3);

        ReservationRequest reservationRequest4 = new ReservationRequest();
        reservationRequest4.setCarId(4);
        reservationRequest4.setMemberId("member4");
        reservationRequest4.setRentalDate(LocalDate.of(2023, 4, 15));
        reservationService.createReservation(reservationRequest4);

        ReservationRequest reservationRequest5 = new ReservationRequest();
        reservationRequest5.setCarId(5);
        reservationRequest5.setMemberId("member2");
        reservationRequest5.setRentalDate(LocalDate.of(2023, 5, 20));
        reservationService.createReservation(reservationRequest5);

        setupUserWithRoleUsers();



    }

    private void setupUserWithRoleUsers() {

        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
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
