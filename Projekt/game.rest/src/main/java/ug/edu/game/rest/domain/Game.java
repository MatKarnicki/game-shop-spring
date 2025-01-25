package ug.edu.game.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;
import ug.edu.game.rest.validation.DateAfter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private GameDetails gameDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "game_franchise_id")
    private GameFranchise franchise;

    @JsonIgnore
    @OneToMany(mappedBy = "game", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<GameOffer> gameOffers = new ArrayList<>();

    @NonNull
    @NotBlank(message = "Title cannot be empty.")
    @Pattern(regexp = "^[A-Z0-9].*", message = "Title must begin with a capital letter or a number.")
    private String title;

    @NonNull
    @NotBlank(message = "Genre cannot be empty.")
    private String genre;

    @NonNull
    @NotNull(message = "Sales cannot be empty.")
    @PositiveOrZero(message = "Sales must be a non-negative number.")
    private Integer sales;


    @Formula("(release_date < current_date + INTERVAL 1 DAY)")
    private Boolean isReleased;

    @NonNull
    @NotNull(message = "Release date is required.")
    @DateAfter("1958-10-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

}
