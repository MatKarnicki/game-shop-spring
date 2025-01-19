package ug.edu.game.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameShop;
import ug.edu.game.rest.exception.GameNotFoundException;
import ug.edu.game.rest.exception.GameNotInFranchiseException;
import ug.edu.game.rest.exception.GameShopNotFoundException;
import ug.edu.game.rest.repository.GameFranchiseRepository;
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
    private final GameFranchiseRepository gameFranchiseRepository;
    private final GameRepository gameRepository;

    @Autowired
    public GameShopService(GameShopRepository gameShopRepository, GameFranchiseRepository gameFranchiseRepository, GameRepository gameRepository) {
        this.gameShopRepository = gameShopRepository;
        this.gameFranchiseRepository = gameFranchiseRepository;
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
        updatedGameShop.setGames(existingGameShop.getGames());
        return gameShopRepository.save(updatedGameShop);
    }

    @Transactional
    public void deleteGameShop(UUID id) {
        GameShop gameShop = gameShopRepository.findById(id).orElseThrow(GameShopNotFoundException::new);
        gameShopRepository.delete(gameShop);
    }

    @Transactional
    public GameShop addGameToShop(UUID gameShopId, UUID gameId) {
        GameShop existingGameShop = gameShopRepository.findById(gameShopId).orElseThrow(GameShopNotFoundException::new);
        Game existingGame = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        existingGameShop.getGames().add(existingGame);
        return gameShopRepository.save(existingGameShop);
    }

    @Transactional
    public void removeGameFromShop(UUID gameShopId, UUID gameId) {
        GameShop existingGameShop = gameShopRepository.findById(gameShopId).orElseThrow(GameShopNotFoundException::new);
        Game existingGame = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        if (!existingGameShop.getGames().contains(existingGame)) {
            throw new GameNotInFranchiseException();
        }
        existingGameShop.getGames().remove(existingGame);
        gameShopRepository.save(existingGameShop);
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
