package ug.edu.game.rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class GameDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Developer name cannot be empty.")
    private String developer;

    private LocalDate developerFounded;

    @NotBlank(message = "Publisher name cannot be empty.")
    private String publisher;

    private LocalDate publisherFounded;

    private String countryDeveloper;

    private String countryPublisher;

    @Column(length = 2000)
    private String description;

    public GameDetails() {}

    public GameDetails(String developer, String publisher, String description, LocalDate developerFounded,
                       LocalDate publisherFounded, String countryDeveloper, String countryPublisher) {
        this.developer = developer;
        this.publisher = publisher;
        this.description = description;
        this.developerFounded = developerFounded;
        this.publisherFounded = publisherFounded;
        this.countryDeveloper = countryDeveloper;
        this.countryPublisher = countryPublisher;
    }

    public UUID getId() {
        return id;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeveloperFounded() {
        return developerFounded;
    }

    public void setDeveloperFounded(LocalDate developerFounded) {
        this.developerFounded = developerFounded;
    }

    public LocalDate getPublisherFounded() {
        return publisherFounded;
    }

    public void setPublisherFounded(LocalDate publisherFounded) {
        this.publisherFounded = publisherFounded;
    }

    public String getCountryDeveloper() {
        return countryDeveloper;
    }

    public void setCountryDeveloper(String countryDeveloper) {
        this.countryDeveloper = countryDeveloper;
    }

    public String getCountryPublisher() {
        return countryPublisher;
    }

    public void setCountryPublisher(String countryPublisher) {
        this.countryPublisher = countryPublisher;
    }

    @Override
    public String toString() {
        return "GameDetails [id=" + id + ", developer=" + developer +
                ", developerFounded=" + developerFounded +
                ", publisher=" + publisher +
                ", publisherFounded=" + publisherFounded +
                ", countryDeveloper=" + countryDeveloper +
                ", countryPublisher=" + countryPublisher +
                ", description=" + description + "]";
    }
}
