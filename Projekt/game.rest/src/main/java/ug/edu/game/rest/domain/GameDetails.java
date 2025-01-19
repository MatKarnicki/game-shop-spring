package ug.edu.game.rest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class GameDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    @NotBlank(message = "Developer name cannot be empty.")
    private String developer;

    @NonNull
    private LocalDate developerFounded;

    @NonNull
    private String countryDeveloper;

    @NonNull
    @Size(min = 3, max = 255, message = "Title must contain at least 3 characters and at most 255 characters")
    private String description;


}
