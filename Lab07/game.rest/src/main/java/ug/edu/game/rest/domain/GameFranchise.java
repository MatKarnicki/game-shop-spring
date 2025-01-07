package ug.edu.game.rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameFranchise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotBlank(message = "Franchise name cannot be empty.")
    private String name;

    private String publisher;

    private String genre;

    @PositiveOrZero(message = "Revenue must be zero or a positive value.")
    private float revenue;

    private String awards;

    private LocalDate lastReleaseDate;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL)
    private List<Game> games = new ArrayList<>();

    public GameFranchise() {}

    public GameFranchise(String name, String publisher, String genre, float revenue, String awards, LocalDate lastReleaseDate) {
        this.name = name;
        this.publisher = publisher;
        this.genre = genre;
        this.revenue = revenue;
        this.awards = awards;
        this.lastReleaseDate = lastReleaseDate;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public LocalDate getLastReleaseDate() {
        return lastReleaseDate;
    }

    public void setLastReleaseDate(LocalDate lastReleaseDate) {
        this.lastReleaseDate = lastReleaseDate;
    }

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
        game.setFranchise(this);
    }

    public void removeGame(Game game) {
        games.remove(game);
        game.setFranchise(null);
    }
}
