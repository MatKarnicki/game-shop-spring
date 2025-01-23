package ug.edu.game.rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameOffer;
import ug.edu.game.rest.dto.GameOfferDto;
import ug.edu.game.rest.dto.GameToShopDto;
import ug.edu.game.rest.dto.ShopWithoutOfferDto;
import ug.edu.game.rest.service.GameOfferService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/game-offer")
public class GameOfferController {
    private final GameOfferService gameOfferService;

    @Autowired
    public GameOfferController(GameOfferService gameOfferService) {
        this.gameOfferService = gameOfferService;
    }

    @PatchMapping("/{shopId}")
    public GameOffer addGameToShop(@PathVariable UUID shopId, @RequestBody @Valid GameToShopDto gameToShopDto) {
        return gameOfferService.addGameToShop(shopId, gameToShopDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{gameOfferId}")
    public void deleteGameFromShopById(@PathVariable UUID gameOfferId) {
        gameOfferService.removeGameFromShop(gameOfferId);
    }

    @GetMapping("/shop-with-developer")
    public List<ShopWithoutOfferDto> getShopWithDeveloper(@RequestParam String developer) {
        return gameOfferService.findShopsByGameDeveloper(developer);
    }

    @GetMapping("{shopId}/all-games-by-price")
    public List<Game> findAllGamesFromShopOrderedByPrice(@PathVariable UUID shopId) {
        return gameOfferService.findAllGamesFromShopOrderedByPrice(shopId);
    }

    @GetMapping("{gameId}/lowest-price")
    public GameOffer findTopByGame_IdOrderByPriceAsc(@PathVariable UUID gameId) {
        return gameOfferService.findTopByGame_IdOrderByPriceAsc(gameId);
    }

    @GetMapping("all-offers")
    public List<GameOfferDto> findAllByGame_Title(@RequestParam String title) {
        return gameOfferService.findAllByGame_Title(title);
    }
}