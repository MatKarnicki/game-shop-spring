package ug.edu.game.rest.domain;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import ug.edu.game.rest.validation.DateAfter;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

 @Entity
public class Game {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private final UUID id;

//     @OneToOne
//     private GameDetails gameDetails;
//
//     @ManyToOne
//     @JoinColumn(name = "game_franchise_id")
//     private GameFranchise franchise;
//
//     public GameFranchise getFranchise() {
//         return franchise;
//     }
//
//     public void setFranchise(GameFranchise franchise) {
//         this.franchise = franchise;
//     }
//
//     @ManyToMany(mappedBy = "games")
//     private List<GameShop> shops = new ArrayList<>();
//
//     public List<GameShop> getShops() {
//         return shops;
//     }

     @NotBlank(message = "Title cannot be empty or null.")
     @Pattern(regexp = "^[A-Z0-9].*", message = "Title must begin with a capital letter or a number.")
     private String title;

     @NotBlank(message = "Genre cannot be empty or null.")
     private String genre;

     @PositiveOrZero(message = "Sales must be a non-negative number.")
     private int sales;

     private boolean released;

     @NotNull(message = "Release date is required.")
     @DateAfter("1958-10-01")
     @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
     public Game() {
         this.id = UUID.randomUUID();
     }

    public Game(String title, String genre, LocalDate releaseDate, int sales) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.sales = sales;
        this.released = releaseDate.isBefore(LocalDate.now().plusDays(1));
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getSales() {
        return sales;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public boolean isReleased() {
        return released;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setReleased(boolean isReleased) {
        this.released = isReleased;
    }

//     public void setShops(List<GameShop> shops) {
//         this.shops = shops;
//     }

     @Override
    public String toString() {
        return "Game [id=" + id + ", title=" + title + ", genre=" + genre +
                ", releaseDate=" + releaseDate + ", sales=" + sales +
                ", released=" + released + "]";
    }
}
