package dat3.car.api;


import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Reservation;
import dat3.car.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Reservation createReservation(@RequestBody ReservationRequest body) {
         return reservationService.createReservation(body);
     }

     @GetMapping(path = "/{reservation_id}")
     ReservationResponse getReservation(@PathVariable int reservation_id) {
            return reservationService.getReservation(reservation_id);
        }

        @GetMapping
        List<ReservationResponse> getReservations() {
            return reservationService.getReservations();
        }


}
