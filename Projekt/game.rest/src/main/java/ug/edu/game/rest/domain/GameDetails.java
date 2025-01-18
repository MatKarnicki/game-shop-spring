package ug.edu.game.rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Publisher name cannot be empty.")
    private String publisher;

    @NonNull
    private LocalDate publisherFounded;

    @NonNull
    private String countryDeveloper;

    @NonNull
    private String countryPublisher;

    @NonNull
    @Size(min = 3,max = 255, message = "Title must contain at least 3 characters and at most 255 characters")
    private String description;


}
