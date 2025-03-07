package ug.edu.game.rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameDetails;
import ug.edu.game.rest.service.GameService;

import java.net.URI;
import java.time.LocalDate;
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
    public Game getGameById(@PathVariable UUID id) {
        return gameService.getGameById(id);
    }

    @PostMapping
    public ResponseEntity<Game> addGame(@Valid @RequestBody Game game) {
        Game createdGame = gameService.addGame(game);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(createdGame.getId());
        return ResponseEntity.created(location).body(createdGame);
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable UUID id, @Valid @RequestBody Game updatedGame) {
        return gameService.updateGame(id, updatedGame);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable UUID id) {
        gameService.deleteGame(id);
    }

    @PostMapping("/{gameId}/details")
    public ResponseEntity<GameDetails> addGameDetails(@PathVariable UUID gameId, @Valid @RequestBody GameDetails gameDetails) {
        GameDetails createdDetails = gameService.addGameDetails(gameId, gameDetails);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(createdDetails.getId());
        return ResponseEntity.created(location).body(createdDetails);
    }

    @PutMapping("/{gameId}/details")
    public GameDetails updateGameDetails(@PathVariable UUID gameId,
                                         @Valid @RequestBody GameDetails updatedDetails) {
        return gameService.updateGameDetails(gameId, updatedDetails);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{gameId}/details")
    public void removeGameDetails(@PathVariable UUID gameId) {
        gameService.deleteGameDetails(gameId);
    }

    @GetMapping("/findDeveloper")
    public List<Game> findAllByGameDetailsDeveloperEqualsIgnoreCase(@RequestParam String developer) {
        return gameService.findAllByGameDetailsDeveloperEqualsIgnoreCase(developer);
    }

    @GetMapping("/betweenDates")
    public List<Game> findAllByReleaseDateIsAfterAndReleaseDateBefore(@RequestParam LocalDate releaseDateFloor, @RequestParam LocalDate releaseDateCeiling) {
        return gameService.findAllByReleaseDateIsAfterAndReleaseDateBefore(releaseDateFloor, releaseDateCeiling);
    }

    @GetMapping("/games-by-franchise")
    public List<Game> findAllByGameDetailsFranchise(@RequestParam UUID franchiseId) {
        return gameService.findByFranchise_Id(franchiseId);
    }

    @GetMapping("/games-outside-franchise/{franchiseId}")
    public List<Game> getAllGamesNotInFranchise(@RequestParam UUID franchiseId) {
        return gameService.getAllGamesNotInFranchise(franchiseId);
    }
}
