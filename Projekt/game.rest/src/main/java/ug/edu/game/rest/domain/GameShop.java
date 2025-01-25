package ug.edu.game.rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class GameShop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    @NotBlank(message = "Shop name cannot be empty.")
    private String name;

    @NonNull
    @NotBlank(message = "Address cannot be empty.")
    private String address;

    @NonNull
    @Pattern(regexp = "(\\+)?([0-9]{2}[( .-]?)?([0-9]{3})[) .-]?([0-9]{3})[ .-]?([0-9]{3})", message = "Phone number is not valid.")
    private String phoneNumber;

    @NonNull
    private LocalTime openingTime;

    @NonNull
    private LocalTime closingTime;

    @NonNull
    @PastOrPresent(message = "Established date must be in the past or present.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate establishedDate;

    @OneToMany(mappedBy = "gameShop", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<GameOffer> gameOffers = new ArrayList<>();

}
