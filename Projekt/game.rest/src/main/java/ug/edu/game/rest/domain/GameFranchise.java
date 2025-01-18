package ug.edu.game.rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class GameFranchise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NonNull
    @NotBlank(message = "Franchise name cannot be empty.")
    private String name;

    @NonNull
    private String publisher;

    @NonNull
    private String genre;

    @NonNull
    @PositiveOrZero(message = "Revenue must be zero or a positive value.")
    private Float revenue;

    private String awards;

    private LocalDate lastReleaseDate;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL)
    private List<Game> games = new ArrayList<>();
}
