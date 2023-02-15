package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

//    Implement a Service method that will allow Members to make a reservation for a Car (assume he had used another endpoint to get the id for the car, and have decided for a date)
//First, let the member reserve a car, even if it's already reserved

private final ReservationRepository reservationRepository;
private final CarRepository carRepository;
private final MemberRepository memberRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    public ReservationResponse makeReservation (ReservationRequest body) {
        try {
          if (body.getCarId() == null || body.getRentalDate() == null || body.getUsername() == null) {
              throw new Exception("Missing data");
          }
          else {
              Reservation reservation = new Reservation();
          }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<ReservationResponse> getReservations () {
           List<Reservation> reservations = reservationRepository.findAll();

           return reservations.stream().map(ReservationResponse::new).toList();

    }

    public ReservationResponse getReservation (int reservation_id) {
       return reservationRepository.findById(reservation_id).map(ReservationResponse::new).orElse(null);
    }
}
