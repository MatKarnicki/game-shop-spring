package ug.edu.game.rest.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class GameShop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Shop name cannot be empty.")
    private String name;

    @NotBlank(message = "Address cannot be empty.")
    private String address;

    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Phone number is not valid.")
    private String phoneNumber;

    @PastOrPresent(message = "Established date must be in the past or present.")
    private LocalDate establishedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_shop_games",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games = new ArrayList<>();

    public GameShop() {}

    public GameShop(String name, String address, String phoneNumber, LocalDate establishedDate) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.establishedDate = establishedDate;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(LocalDate establishedDate) {
        this.establishedDate = establishedDate;
    }

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
        game.getShops().add(this);
    }

    public void removeGame(Game game) {
        games.remove(game);
        game.getShops().remove(this);
    }
}
