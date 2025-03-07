package ug.edu.game.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ug.edu.game.rest.domain.GameShop;
import ug.edu.game.rest.dto.ShopRevenueDto;
import ug.edu.game.rest.dto.ShopWithoutOfferDto;
import ug.edu.game.rest.service.GameShopService;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/game-shop")
public class GameShopController {
    private final GameShopService gameShopService;

    @Autowired
    public GameShopController(GameShopService gameShopService) {
        this.gameShopService = gameShopService;
    }

    @GetMapping
    public List<GameShop> getAllGameShops() {
        return gameShopService.getAllGameShops();
    }

    @GetMapping("/{id}")
    public GameShop getGameShopById(@PathVariable UUID id) {
        return gameShopService.getGameShopById(id);
    }

    @PostMapping
    public ResponseEntity<GameShop> addGameShop(@RequestBody GameShop gameShop) {
        GameShop createdGameShop = gameShopService.addGameShop(gameShop);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(createdGameShop.getId());
        return ResponseEntity.created(location).body(createdGameShop);
    }

    @PutMapping("/{id}")
    public GameShop updateGameShop(@PathVariable UUID id, @RequestBody GameShop gameShop) {
        return gameShopService.updateGameShop(id, gameShop);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGameShopById(@PathVariable UUID id) {
        gameShopService.deleteGameShop(id);
    }

    @GetMapping("/find-by-name")
    public List<ShopWithoutOfferDto> findAllByAddressContainingIgnoreCase(@RequestParam String address) {
        return gameShopService.findAllByAddressContainingIgnoreCase(address);
    }

    @GetMapping("/currently-open")
    public List<ShopWithoutOfferDto> getCurrentlyOpenShops(@RequestParam LocalTime time) {
        return gameShopService.findAllCurrentlyOpen(time);
    }

    @GetMapping("/{shopId}/revenue")
    public ShopRevenueDto calculateExpectedRevenueForShop(@PathVariable UUID shopId) {
        return gameShopService.calculateExpectedRevenueForShop(shopId);
    }


}
