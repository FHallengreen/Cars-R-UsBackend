package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.ReservationRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
    @Setter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class MemberResponse {
    String username; //Remember this is the primary key
    String email;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;
    Integer ranking;
    Boolean approved;
    List<ReservationResponse> reservations;

    //Convert Member Entity to Member DTO
    public MemberResponse(Member m, boolean includeAll, boolean includeReservations) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.street = m.getStreet();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
        this.city = m.getCity();
        this.zip = m.getZip();
        if (includeAll) {
            this.created = m.getCreated();
            this.edited = m.getLastEdited();
            this.approved = m.isApproved();
            this.ranking = m.getRanking();
        }
        if (includeReservations) {
            this.reservations = new ArrayList<>();
            for (Reservation r : m.getReservations()) {
                this.reservations.add(new ReservationResponse(r));
        }

    }
    }
}
