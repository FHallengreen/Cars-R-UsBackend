package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.security.entity.Role;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

//    Implement a Service method that will allow Members to make a reservation for a Car (assume he had used another endpoint to get the id for the car, and have decided for a date)
//First, let the member reserve a car, even if it's already reserved

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final MemberRepository memberRepository;
    private final UserWithRolesRepository userWithRolesRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, MemberRepository memberRepository, UserWithRolesRepository userWithRolesRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.userWithRolesRepository = userWithRolesRepository;
    }



    public Reservation createReservation(ReservationRequest reservationRequest) {
        if (reservationRequest.getCar() == null || reservationRequest.getRentalDate() == null || reservationRequest.getMember() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Missing data");
        }

            Car car = new Car(reservationRequest.getCar().getBrand(),reservationRequest.getCar().getModel(),reservationRequest.getCar().getPricePrDay(),reservationRequest.getCar().getBestDiscount());
            carRepository.save(car);

            Member member = new Member(reservationRequest.getMember().getUsername(),reservationRequest.getMember().getEmail(), reservationRequest.getMember().getPassword(), reservationRequest.getMember().getFirstName(), reservationRequest.getMember().getLastName(), reservationRequest.getMember().getStreet(), reservationRequest.getMember().getCity(), reservationRequest.getMember().getZip());
            memberRepository.save(member);
            UserWithRoles user1 = new UserWithRoles();
            user1.setUsername(reservationRequest.getMember().getUsername());
            user1.setPassword(reservationRequest.getMember().getPassword());
            user1.setEmail(reservationRequest.getMember().getEmail());
            user1.addRole(Role.USER);
//            userWithRolesRepository.save(user1);

        LocalDate rentalDate = reservationRequest.getRentalDate();
        if (reservationRepository.existsByCarAndRentalDate(car, rentalDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car already reserved for the requested rental date");
        }
        else if (rentalDate.isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Rental date must be in the future");
        }
        else {
            Reservation reservation = new Reservation(car, member, reservationRequest.getRentalDate());
            reservationRepository.save(reservation);
            return reservation;
        }
    }

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(ReservationResponse::new).toList();

    }

    public ReservationResponse getReservation(int reservation_id) {
        return reservationRepository.findById(reservation_id).map(ReservationResponse::new).orElse(null);
    }
}
