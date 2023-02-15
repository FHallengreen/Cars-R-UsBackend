package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private boolean approved;
    private int ranking;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;

    @ElementCollection
    List<String> favoriteCarColors = new ArrayList<>();

    @ElementCollection
    @MapKeyColumn(name = "description")
    @Column(name = "phone_number")
    Map<String,String> phones = new HashMap<>();

    /*
By adding @JsonIgnore to the cars field in the Owner entity, the circular reference between Car and Owner is broken,
and the JSON serialization should work without errors.

Alternatively, you can use the Jackson @JsonManagedReference and @JsonBackReference annotations to create a bidirectional
relationship between entities without causing infinite recursion. With this approach, you use @JsonManagedReference on
the parent side of the relationship and @JsonBackReference on the child side. The @JsonManagedReference annotation
instructs Jackson to serialize the annotated field, and the @JsonBackReference annotation instructs Jackson to ignore
the annotated field during serialization.*/

    @JsonManagedReference
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Member(String user, String email, String password, String firstName, String lastName, String street, String city, String zip) {
        super(user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", approved=" + approved +
                ", ranking=" + ranking +
                ", created=" + created +
                ", lastEdited=" + lastEdited +
                ", favoriteCarColors=" + favoriteCarColors +
                ", phones=" + phones +
                ", reservations=" + reservations +
                '}';
    }
}
