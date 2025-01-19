package ug.edu.game.rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ug.edu.game.rest.domain.GameFranchise;
import ug.edu.game.rest.dto.GameToFranchiseDto;
import ug.edu.game.rest.service.GameFranchiseService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/game-franchise")
public class GameFranchiseController {
    private final GameFranchiseService gameFranchiseService;

    @Autowired
    public GameFranchiseController(GameFranchiseService gameFranchiseService) {
        this.gameFranchiseService = gameFranchiseService;
    }

    @GetMapping
    public List<GameFranchise> getAllGameFranchise() {
        return gameFranchiseService.getAllGameFranchises();
    }

    @GetMapping("/{id}")
    public GameFranchise getGameFranchiseById(@PathVariable UUID id) {
        return gameFranchiseService.getGameFranchiseById(id);
    }

    @PostMapping
    public ResponseEntity<GameFranchise> addGameFranchise(@RequestBody GameFranchise gameFranchise) {
        GameFranchise createdGameFranchise = gameFranchiseService.addGameFranchise(gameFranchise);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(createdGameFranchise.getId());
        return ResponseEntity.created(location).body(createdGameFranchise);
    }

    @PutMapping("/{id}")
    public GameFranchise updateGameFranchise(@PathVariable UUID id, @RequestBody GameFranchise gameFranchise) {
        return gameFranchiseService.updateGameFranchise(id, gameFranchise);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGameFranchiseById(@PathVariable UUID id) {
        gameFranchiseService.deleteGameFranchise(id);
    }

    @PatchMapping("/{franchiseId}/game")
    public GameFranchise addGameToFranchise(@PathVariable UUID franchiseId, @RequestBody @Valid GameToFranchiseDto gameToFranchiseDto) {
        return gameFranchiseService.addGameToFranchise(franchiseId, gameToFranchiseDto.gameId());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{franchiseId}/game")
    public void deleteGameFromFranchiseById(@PathVariable UUID franchiseId, @RequestBody @Valid GameToFranchiseDto gameToFranchiseDto) {
        gameFranchiseService.removeGameFromFranchise(franchiseId, gameToFranchiseDto.gameId());
    }
}