package ug.edu.game.rest.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class GameOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonIgnore
    @NonNull
    @ManyToOne()
    @JoinColumn(name = "shop_id")
    private GameShop gameShop;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "game_id")
    private Game game;

    @NonNull
    private Integer quantity;

    @NonNull
    private Double price;
}
