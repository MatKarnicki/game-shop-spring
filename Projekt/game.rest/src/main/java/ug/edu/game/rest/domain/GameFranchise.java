package ug.edu.game.rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class GameFranchise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

    private Integer awards;

    @JsonProperty(access = READ_ONLY)
    @Formula("(SELECT MAX(g.release_date) FROM Game g WHERE g.game_franchise_id = id)")
    private LocalDate lastReleaseDate;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Game> games = new ArrayList<>();
}
