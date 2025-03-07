package ug.edu.game.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameOffer;
import ug.edu.game.rest.domain.GameShop;
import ug.edu.game.rest.dto.GameOfferDto;

import java.util.List;
import java.util.UUID;

public interface GameOfferRepository extends JpaRepository<GameOffer, UUID> {
    @Query("SELECT DISTINCT gs FROM GameShop gs " +
            "JOIN GameOffer go ON gs.id = go.gameShop.id " +
            "JOIN Game g ON go.game.id = g.id " +
            "JOIN GameDetails gd ON g.gameDetails.id = gd.id " +
            "WHERE LOWER(gd.developer) = LOWER(:developer)")
    List<GameShop> findShopsByGameDeveloper(@Param("developer") String developer);

    @Query("SELECT g FROM Game g " +
            "JOIN GameOffer go ON g.id = go.game.id " +
            "JOIN GameShop gs ON go.gameShop.id = gs.id " +
            "WHERE gs.id = :shopId " +
            "ORDER BY go.price ASC")
    List<Game> findAllGamesFromShopOrderedByPrice(@Param("shopId") UUID shopId);

    GameOffer findTopByGame_IdOrderByPriceAsc(UUID gameId);

    @Query("SELECT new ug.edu.game.rest.dto.GameOfferDto(gs.id, gs.name, g.title, go.price) " +
            "FROM GameOffer go " +
            "JOIN go.game g " +
            "JOIN go.gameShop gs " +
            "WHERE g.title = :title")
    List<GameOfferDto> findAllByGame_Title(@Param("title") String title);

}
