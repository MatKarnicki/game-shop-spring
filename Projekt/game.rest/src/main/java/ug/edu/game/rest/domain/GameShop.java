package ug.edu.game.rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

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
//    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Phone number is not valid.")
    private String phoneNumber;

    @NonNull
    private LocalTime openingTime;

    @NonNull
    private LocalTime closingTime;

    @NonNull
    @PastOrPresent(message = "Established date must be in the past or present.")
    private LocalDate establishedDate;

    @OneToMany(mappedBy = "gameShop", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<GameOffer> gameOffers = new ArrayList<>();

}
