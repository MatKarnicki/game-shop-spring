package ug.edu.game.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.GameFranchise;
import ug.edu.game.rest.exception.GameFranchiseNotFoundException;
import ug.edu.game.rest.repository.GameFranchiseRepository;
import ug.edu.game.rest.repository.GameRepository;

import java.util.List;
import java.util.UUID;

@Service
public class GameFranchiseService {
    private final GameFranchiseRepository gameFranchiseRepository;
    private final GameRepository gameRepository;

    @Autowired
    public GameFranchiseService(GameFranchiseRepository gameFranchiseRepository, GameRepository gameRepository) {
        this.gameFranchiseRepository = gameFranchiseRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<GameFranchise> getAllGameFranchises() {
        return gameFranchiseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public GameFranchise getGameFranchiseById(UUID id) {
        return gameFranchiseRepository.findById(id).orElseThrow(() -> new GameFranchiseNotFoundException());
    }

    @Transactional
    public GameFranchise addGameFranchise(GameFranchise gameFranchise) {
        return gameFranchiseRepository.save(gameFranchise);
    }

    @Transactional
    public GameFranchise updateGameFranchise(UUID id, GameFranchise updatedGameFranchise) {
        GameFranchise existingGameFranchise = gameFranchiseRepository.findById(id)
                .orElseThrow(GameFranchiseNotFoundException::new);
        updatedGameFranchise.setId(existingGameFranchise.getId());
        return gameFranchiseRepository.save(updatedGameFranchise);
    }

    @Transactional
    public void deleteGameFranchise(UUID id) {
        GameFranchise gameFranchise = gameFranchiseRepository.findById(id)
                .orElseThrow(GameFranchiseNotFoundException::new);
        gameFranchiseRepository.delete(gameFranchise);
    }

    @Transactional
    public void initializeFranchises() {
        gameFranchiseRepository.save(new GameFranchise("Souls Series", "FromSoftware", "Souls-like", 75000000f));
        gameFranchiseRepository.save(new GameFranchise("Monster Hunter", "Capcom", "RPG", 27000000f));
        gameFranchiseRepository.save(new GameFranchise("Final Fantasy", "Square Enix", "RPG", 200000000f));
        gameFranchiseRepository.save(new GameFranchise("Wiedźmin", "CD Projekt Red", "RPG", 52000000f));
        gameFranchiseRepository.save(new GameFranchise("Devil May Cry", "Capcom", "Character-Action", 9000000f));
        gameFranchiseRepository.save(new GameFranchise("Rocket League", "Psyonix", "Sports", 10000000f));
        gameFranchiseRepository.save(new GameFranchise("Stardew Valley", "ConcernedApe", "Simulation", 20000000f));
        gameFranchiseRepository.save(new GameFranchise("Animal Crossing", "Nintendo", "Simulation", 100000000f));
        gameFranchiseRepository.save(new GameFranchise("Overwatch", "Blizzard Entertainment", "Shooter", 50000000f));
    }
}
