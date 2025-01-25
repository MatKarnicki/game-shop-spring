package ug.edu.game.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameFranchise;
import ug.edu.game.rest.dto.FranchiseGameCountDto;
import ug.edu.game.rest.dto.FranchisePerShopDto;
import ug.edu.game.rest.exception.GameFranchiseNotFoundException;
import ug.edu.game.rest.exception.GameNotFoundException;
import ug.edu.game.rest.exception.GameNotInFranchiseException;
import ug.edu.game.rest.repository.GameFranchiseRepository;
import ug.edu.game.rest.repository.GameRepository;

import java.time.LocalDate;
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
        updatedGameFranchise.setGames(existingGameFranchise.getGames());
        return gameFranchiseRepository.save(updatedGameFranchise);
    }

    @Transactional
    public void deleteGameFranchise(UUID id) {
        GameFranchise gameFranchise = gameFranchiseRepository.findById(id)
                .orElseThrow(GameFranchiseNotFoundException::new);
        gameFranchiseRepository.delete(gameFranchise);
    }

    @Transactional
    public GameFranchise addGameToFranchise(UUID gameFranchiseId, UUID gameId) {
        GameFranchise existingGameFranchise = gameFranchiseRepository.findById(gameFranchiseId).orElseThrow(GameFranchiseNotFoundException::new);
        Game existingGame = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        existingGame.setFranchise(existingGameFranchise);
        return gameFranchiseRepository.save(existingGameFranchise);
    }

    @Transactional
    public void removeGameFromFranchise(UUID gameFranchiseId, UUID gameId) {
        GameFranchise existingGameFranchise = gameFranchiseRepository.findById(gameFranchiseId).orElseThrow(GameFranchiseNotFoundException::new);
        Game existingGame = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        if (!existingGameFranchise.equals(existingGame.getFranchise())) {
            throw new GameNotInFranchiseException();
        }
        existingGame.setFranchise(null);
        gameRepository.save(existingGame);
    }

    @Transactional(readOnly = true)
    public List<GameFranchise> findByLastReleaseDateAfter(LocalDate lastReleaseDate) {
        return gameFranchiseRepository.findByLastReleaseDateAfter(lastReleaseDate);
    }

    @Transactional(readOnly = true)
    public List<FranchisePerShopDto> findShopsWithGameCountFromFranchise(String franchise) {
        return gameFranchiseRepository.findShopsWithGameCountFromFranchise(franchise);
    }

    @Transactional(readOnly = true)
    public List<FranchiseGameCountDto> findAllFranchisesWithGameCount() {
        return gameFranchiseRepository.findAllFranchisesWithGameCount();
    }

    public Page<GameFranchise> getPaginatedFranchises(Pageable pageable) {
        return gameFranchiseRepository.findAll(pageable);
    }
}
