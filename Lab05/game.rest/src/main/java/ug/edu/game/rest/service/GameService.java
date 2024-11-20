package ug.edu.game.rest.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import ug.edu.game.rest.model.Game;
import ug.edu.game.rest.exception.GameNotFoundException;

@Service
public class GameService {
    private final Map<String, Game> gameDatabase = new HashMap<>();

    public List<Game> getAllGames() {
        return new ArrayList<>(gameDatabase.values());
    }

    public Game getGameById(String id) {
        return Optional.ofNullable(gameDatabase.get(id))
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
    }

    public Game addGame(Game game) {
        gameDatabase.put(game.getId(), game);
        return game;
    }

    public Game updateGame(String id, Game updatedGame) {
        Game existingGame = Optional.ofNullable(gameDatabase.get(id))
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));

        // Update only the information provided in the updatedGame object
        existingGame.setTitle(Optional.ofNullable(updatedGame.getTitle()).orElse(existingGame.getTitle()));
        existingGame.setGenre(Optional.ofNullable(updatedGame.getGenre()).orElse(existingGame.getGenre()));
        existingGame.setReleaseDate(Optional.ofNullable(updatedGame.getReleaseDate()).orElse(existingGame.getReleaseDate()));
        existingGame.setSales(Optional.of(updatedGame.getSales()).orElse(existingGame.getSales()));
        existingGame.setReleased(updatedGame.isReleased());

        gameDatabase.put(id, existingGame);
        return existingGame;
    }

    public void deleteGame(String id) {
        Optional.ofNullable(gameDatabase.get(id))
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
        gameDatabase.remove(id);
    }

    public void initializeDatabase() {
        Game game1 = new Game("Bloodborne", "Action", LocalDate.of(2015, 10, 11), 10000000);
        Game game2 = new Game("Monster Hunter World", "RPG", LocalDate.of(2018, 2, 15), 15000000);
        Game game3 = new Game("Final Fantasy XVI", "RPG", LocalDate.of(2023, 6, 3), 3000000);

        gameDatabase.put(game1.getId(), game1);
        gameDatabase.put(game2.getId(), game2);
        gameDatabase.put(game3.getId(), game3);
    }
}
