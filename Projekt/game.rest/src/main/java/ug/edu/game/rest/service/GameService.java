package ug.edu.game.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameDetails;
import ug.edu.game.rest.repository.GameRepository;
import ug.edu.game.rest.exception.GameNotFoundException;
import ug.edu.game.rest.exception.GameDetailsNotFoundException;

import java.time.LocalDate;
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
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
    }

    @Transactional
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    @Transactional
    public Game updateGame(UUID id, Game updatedGame) {
            Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
        updatedGame.setId(existingGame.getId());
        updatedGame.setIsReleased(updatedGame.getReleaseDate().isBefore(LocalDate.now().plusDays(1)));

        return gameRepository.save(updatedGame);
    }

    @Transactional
    public void deleteGame(UUID id) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
        gameRepository.delete(existingGame);
    }

    @Transactional
    public void initializeDatabase() {
        Game game1 = new Game("Bloodborne", "Action", LocalDate.of(2015, 10, 11), 10000000);
        Game game2 = new Game("Monster Hunter World", "RPG", LocalDate.of(2018, 2, 15), 15000000);
        Game game3 = new Game("Final Fantasy XVI", "RPG", LocalDate.of(2023, 6, 3), 3000000);

        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
    }

    @Transactional
    public GameDetails addGameDetails(UUID gameId, GameDetails gameDetails) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + gameId + " not found"));

        game.setGameDetails(gameDetails);
        gameRepository.save(game);
        return game.getGameDetails();
    }

    @Transactional
    public GameDetails updateGameDetails(UUID gameId, String detailsId, GameDetails updatedDetails) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + gameId + " not found"));

        GameDetails existingDetails = game.getGameDetails();
        if (existingDetails == null || !existingDetails.getId().equals(UUID.fromString(detailsId))) {
            throw new GameDetailsNotFoundException("GameDetails with ID " + detailsId + " not found for game ID " + gameId);
        }
        updatedDetails.setId(existingDetails.getId());
        game.setGameDetails(updatedDetails);
        gameRepository.save(game);
        return updatedDetails;
    }

    @Transactional
    public void deleteGameDetails(UUID gameId, String detailsId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + gameId + " not found"));

        GameDetails existingDetails = game.getGameDetails();
        if (existingDetails == null || !existingDetails.getId().equals(UUID.fromString(detailsId))) {
            throw new GameDetailsNotFoundException("GameDetails with ID " + detailsId + " not found for game ID " + gameId);
        }

        game.setGameDetails(null);
        gameRepository.save(game);
    }
}
