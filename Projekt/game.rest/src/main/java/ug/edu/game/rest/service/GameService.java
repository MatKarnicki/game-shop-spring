package ug.edu.game.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameDetails;
import ug.edu.game.rest.exception.GameDetailsNotFoundException;
import ug.edu.game.rest.exception.GameNotFoundException;
import ug.edu.game.rest.repository.GameRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Game getGameById(UUID id) {
        return gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);
    }

    @Transactional
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    @Transactional
    public Game updateGame(UUID id, Game updatedGame) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);
        updatedGame.setId(existingGame.getId());
        updatedGame.setFranchise(existingGame.getFranchise());
        updatedGame.setGameDetails(existingGame.getGameDetails());
        updatedGame.setGameOffers(existingGame.getGameOffers());
        updatedGame.setIsReleased(updatedGame.getReleaseDate().isBefore(LocalDate.now().plusDays(1)));

        return gameRepository.save(updatedGame);
    }

    @Transactional
    public void deleteGame(UUID id) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);
        gameRepository.delete(existingGame);
    }

    @Transactional
    public GameDetails addGameDetails(UUID gameId, GameDetails gameDetails) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);
        game.setGameDetails(gameDetails);
        gameRepository.save(game);
        return game.getGameDetails();
    }

    @Transactional
    public GameDetails updateGameDetails(UUID gameId, String detailsId, GameDetails updatedDetails) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        GameDetails existingDetails = game.getGameDetails();
        if (existingDetails == null || !existingDetails.getId().equals(UUID.fromString(detailsId))) {
            throw new GameDetailsNotFoundException();
        }
        updatedDetails.setId(existingDetails.getId());
        game.setGameDetails(updatedDetails);
        gameRepository.save(game);
        return updatedDetails;
    }

    @Transactional
    public void deleteGameDetails(UUID gameId, String detailsId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        GameDetails existingDetails = game.getGameDetails();
        if (existingDetails == null || !existingDetails.getId().equals(UUID.fromString(detailsId))) {
            throw new GameDetailsNotFoundException();
        }

        game.setGameDetails(null);
        gameRepository.save(game);
    }

    @Transactional
    public void initializeDatabase() {
        gameRepository.saveAll(Arrays.asList(
                new Game("Bloodborne", "Souls-like", LocalDate.of(2015, 10, 11), 10000000),
                new Game("Monster Hunter World", "RPG", LocalDate.of(2018, 2, 15), 15000000),
                new Game("Final Fantasy XVI", "RPG", LocalDate.of(2023, 6, 3), 3000000),
                new Game("Wiedźmin 3: Dziki Gon", "RPG", LocalDate.of(2015, 5, 19), 50000000),
                new Game("Wiedźmin 2: Zabójca Królów", "RPG", LocalDate.of(2011, 5, 17), 2000000),
                new Game("Devil May Cry 5", "Character-Action", LocalDate.of(2019, 3, 8), 6000000),
                new Game("Devil May Cry 4", "Character-Action", LocalDate.of(2008, 1, 31), 3000000),
                new Game("Dark Souls III", "Souls-like", LocalDate.of(2016, 4, 12), 10000000),
                new Game("Dark Souls II", "Souls-like", LocalDate.of(2014, 3, 11), 2500000),
                new Game("Final Fantasy XV", "RPG", LocalDate.of(2016, 11, 29), 10000000),
                new Game("Rocket League", "Sports", LocalDate.of(2015, 7, 7), 10000000),
                new Game("Stardew Valley", "Simulation", LocalDate.of(2016, 2, 26), 20000000),
                new Game("Animal Crossing: New Horizons", "Simulation", LocalDate.of(2020, 3, 20), 42000000),
                new Game("Overwatch", "Shooter", LocalDate.of(2016, 5, 24), 50000000)
        ));
    }


}
