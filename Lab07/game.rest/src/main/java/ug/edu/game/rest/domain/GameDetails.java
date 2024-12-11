package ug.edu.game.rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class GameDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotBlank(message = "Developer name cannot be empty.")
    private String developer;

    @NotBlank(message = "Publisher name cannot be empty.")
    private String publisher;

    private String description;

    @OneToOne
    private Game game;

    public GameDetails() {}

    public GameDetails(String developer, String publisher, String description) {
        this.developer = developer;
        this.publisher = publisher;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GameDetails [id=" + id + ", developer=" + developer +
                ", publisher=" + publisher + ", description=" + description + "]";
    }
}