package ug.edu.game.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameOffer;
import ug.edu.game.rest.domain.GameShop;
import ug.edu.game.rest.dto.GameToShopDto;
import ug.edu.game.rest.exception.GameNotFoundException;
import ug.edu.game.rest.exception.GameShopNotFoundException;
import ug.edu.game.rest.repository.GameOfferRepository;
import ug.edu.game.rest.repository.GameRepository;
import ug.edu.game.rest.repository.GameShopRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class GameShopService {
    private final GameShopRepository gameShopRepository;
    private final GameOfferRepository gameOfferRepository;
    private final GameRepository gameRepository;

    @Autowired
    public GameShopService(GameShopRepository gameShopRepository, GameOfferRepository gameOfferRepository, GameRepository gameRepository) {
        this.gameShopRepository = gameShopRepository;
        this.gameOfferRepository = gameOfferRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<GameShop> getAllGameShops() {
        return gameShopRepository.findAll();
    }

    @Transactional(readOnly = true)
    public GameShop getGameShopById(UUID id) {
        return gameShopRepository.findById(id).orElseThrow(GameShopNotFoundException::new);
    }

    @Transactional
    public GameShop addGameShop(GameShop gameShop) {
        return gameShopRepository.save(gameShop);
    }

    @Transactional
    public GameShop updateGameShop(UUID id, GameShop updatedGameShop) {
        GameShop existingGameShop = gameShopRepository.findById(id).orElseThrow(GameShopNotFoundException::new);
        updatedGameShop.setId(existingGameShop.getId());
        updatedGameShop.setGameOffers(existingGameShop.getGameOffers());
        return gameShopRepository.save(updatedGameShop);
    }

    @Transactional
    public void deleteGameShop(UUID id) {
        GameShop gameShop = gameShopRepository.findById(id).orElseThrow(GameShopNotFoundException::new);
        gameShopRepository.delete(gameShop);
    }

    @Transactional
    public GameOffer addGameToShop(UUID gameShopId, GameToShopDto gameToShopDto) {
        GameShop existingGameShop = gameShopRepository.findById(gameShopId).orElseThrow(GameShopNotFoundException::new);
        Game existingGame = gameRepository.findById(gameToShopDto.gameId()).orElseThrow(GameNotFoundException::new);
        var newGameOffer = new GameOffer(existingGameShop, existingGame, gameToShopDto.quantity(), gameToShopDto.price());
        return gameOfferRepository.save(newGameOffer);
    }

    @Transactional
    public void removeGameFromShop(UUID gameOfferId) {
        GameOffer gameOffer = gameOfferRepository.findById(gameOfferId).orElseThrow(GameNotFoundException::new);
        gameOfferRepository.delete(gameOffer);
    }

    public void initializeShops() {
        gameShopRepository.saveAll(Arrays.asList(
                new GameShop(
                        "GameHub",
                        "Przykładów, Przykładówny 6",
                        "+1234567890",
                        LocalTime.of(9, 0),
                        LocalTime.of(21, 0),
                        LocalDate.of(2010, 5, 20)
                ),
                new GameShop(
                        "Virtual Paradise",
                        "Gdańsk, WitkaStwosza 210",
                        "+9876543210",
                        LocalTime.of(10, 0),
                        LocalTime.of(22, 0),
                        LocalDate.of(2015, 8, 15)),
                new GameShop(
                        "Retro Realm",
                        "Sopot, Recenzyjna 1",
                        "+1122334455",
                        LocalTime.of(8, 30),
                        LocalTime.of(20, 30),
                        LocalDate.of(2005, 11, 10)),
                new GameShop(
                        "Virtual Vault",
                        "Gdynia, Kościuszki 55",
                        "+9988776655",
                        LocalTime.of(11, 0),
                        LocalTime.of(23, 0),
                        LocalDate.of(2020, 1, 1)
                ), new GameShop("Game Vault",
                        "Gdańsk, Kościuszki 66",
                        "+2233445566",
                        LocalTime.of(9, 30),
                        LocalTime.of(21, 30),
                        LocalDate.of(2000, 4, 5)
                )
        ));
    }
}
