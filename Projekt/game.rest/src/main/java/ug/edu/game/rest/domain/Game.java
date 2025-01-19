package ug.edu.game.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    private GameDetails gameDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "game_franchise_id")
    private GameFranchise franchise;

    @JsonIgnore
    @ManyToMany(mappedBy = "games", fetch = FetchType.EAGER)
    private List<GameShop> shops = new ArrayList<>();

    @NonNull
    @NotBlank(message = "Title cannot be empty or null.")
    @Pattern(regexp = "^[A-Z0-9].*", message = "Title must begin with a capital letter or a number.")
    private String title;

    @NonNull
    @NotBlank(message = "Genre cannot be empty or null.")
    private String genre;

    @NonNull
    @PositiveOrZero(message = "Sales must be a non-negative number.")
    private Integer sales;

    private Boolean isReleased;

    @NonNull
    @NotNull(message = "Release date is required.")
    @DateAfter("1958-10-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    public Game(String title, String genre, LocalDate releaseDate, Integer sales) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.sales = sales;
        this.isReleased = releaseDate.isBefore(LocalDate.now().plusDays(1));
    }
}
