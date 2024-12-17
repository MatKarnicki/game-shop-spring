package ug.edu.game.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.repository.GameRepository;
import ug.edu.game.rest.exception.GameNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        existingGame.setTitle(Optional.ofNullable(updatedGame.getTitle()).orElse(existingGame.getTitle()));
        existingGame.setGenre(Optional.ofNullable(updatedGame.getGenre()).orElse(existingGame.getGenre()));
        existingGame.setReleaseDate(Optional.ofNullable(updatedGame.getReleaseDate()).orElse(existingGame.getReleaseDate()));
        existingGame.setSales(updatedGame.getSales());
        existingGame.setReleased(updatedGame.isReleased());

        return gameRepository.save(existingGame);
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
}
