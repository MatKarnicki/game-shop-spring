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

    @Transactional(readOnly = true)
    public List<Game> findAllByGameDetailsDeveloperEqualsIgnoreCase(String developer) {
        return gameRepository.findAllByGameDetailsDeveloperEqualsIgnoreCase(developer);
    }

    @Transactional(readOnly = true)
    public List<Game> findAllByReleaseDateIsAfterAndReleaseDateBefore(LocalDate releaseDateFloor, LocalDate releaseDateCeiling) {
        return gameRepository.findAllByReleaseDateIsAfterAndReleaseDateBefore(releaseDateFloor, releaseDateCeiling);
    }
}
