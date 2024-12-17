package ug.edu.game.rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.service.GameService;
import ug.edu.game.rest.exception.GameNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable UUID id) {
        try {
            Game game = gameService.getGameById(id);
            return ResponseEntity.ok(game);
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@Valid @RequestBody Game game) {
        Game createdGame = gameService.addGame(game);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(createdGame.getId());
        return ResponseEntity.created(location).body(createdGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable UUID id, @Valid @RequestBody Game updatedGame) {
        try {
            Game game = gameService.updateGame(id, updatedGame);
            return ResponseEntity.ok(game);
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable UUID id) {
        try {
            gameService.deleteGame(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted game with ID " + id);
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
