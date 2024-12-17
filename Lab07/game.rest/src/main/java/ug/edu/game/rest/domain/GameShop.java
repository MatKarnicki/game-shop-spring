package ug.edu.game.rest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameShop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Shop name cannot be empty.")
    private String name;

//    @ManyToMany
//    @JoinTable(
//            name = "game_shop_games",
//            joinColumns = @JoinColumn(name = "shop_id"),
//            inverseJoinColumns = @JoinColumn(name = "game_id")
//    )
//    private List<Game> games = new ArrayList<>();

    public GameShop() {}

    public GameShop(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public List<Game> getGames() {
//        return games;
//    }
//
//    public void addGame(Game game) {
//        games.add(game);
//        game.getShops().add(this);
//    }
//
//    public void removeGame(Game game) {
//        games.remove(game);
//        game.getShops().remove(this);
//    }
}
