package ug.edu.game.rest.service;
import org.springframework.stereotype.Service;
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
        if (!gameDatabase.containsKey(id)) {
            throw new GameNotFoundException("Game with ID " + id + " not found");
        }
        return gameDatabase.get(id);
    }

    public Game addGame(Game game) {
        gameDatabase.put(game.getId(), game);
        return game;
    }

    public Game updateGame(String id, Game updatedGame) {
        if (!gameDatabase.containsKey(id)) {
            throw new GameNotFoundException("Game with ID " + id + " not found");
        }
        Game existingGame = gameDatabase.get(id);
        existingGame.setTitle(Optional.ofNullable(updatedGame.getTitle()).orElse(existingGame.getTitle()));
        existingGame.setGenre(Optional.ofNullable(updatedGame.getGenre()).orElse(existingGame.getGenre()));
        existingGame.setReleaseYear(updatedGame.getReleaseYear() != 0 ? updatedGame.getReleaseYear() : existingGame.getReleaseYear());
        gameDatabase.put(id, existingGame);

        return existingGame;
    }

    public void deleteGame(String id) {
        if (!gameDatabase.containsKey(id)) {
            throw new GameNotFoundException("Game with ID " + id + " not found");
        }
        gameDatabase.remove(id);
    }

    public void initializeDatabase() {
        Game game1 = new Game("Bloodborne", "Action", 2015);
        Game game2 = new Game("Monster Hunter World", "RPG", 2018);
        Game game3 = new Game("Final Fantasy XVI", "RPG", 2023);
        gameDatabase.put(game1.getId(), game1);
        gameDatabase.put(game2.getId(), game2);
        gameDatabase.put(game3.getId(), game3);
    }
}
