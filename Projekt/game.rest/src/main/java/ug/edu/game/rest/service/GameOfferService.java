package ug.edu.game.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameOffer;
import ug.edu.game.rest.domain.GameShop;
import ug.edu.game.rest.dto.GameOfferDto;
import ug.edu.game.rest.dto.GameToShopDto;
import ug.edu.game.rest.dto.ShopWithoutOfferDto;
import ug.edu.game.rest.exception.GameNotFoundException;
import ug.edu.game.rest.exception.GameShopNotFoundException;
import ug.edu.game.rest.repository.GameOfferRepository;
import ug.edu.game.rest.repository.GameRepository;
import ug.edu.game.rest.repository.GameShopRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameOfferService {

    private final GameShopRepository gameShopRepository;
    private final GameRepository gameRepository;
    private final GameOfferRepository gameOfferRepository;

    @Autowired
    public GameOfferService(GameShopRepository gameShopRepository,
                            GameRepository gameRepository,
                            GameOfferRepository gameOfferRepository) {
        this.gameShopRepository = gameShopRepository;
        this.gameRepository = gameRepository;
        this.gameOfferRepository = gameOfferRepository;
    }

    @Transactional
    public GameOffer addGameToShop(UUID gameShopId, GameToShopDto gameToShopDto) {
        GameShop existingGameShop = gameShopRepository.findById(gameShopId).orElseThrow(GameShopNotFoundException::new);
        Game existingGame = gameRepository.findById(gameToShopDto.gameId()).orElseThrow(GameNotFoundException::new);
        var newGameOffer = new GameOffer(existingGameShop, existingGame, gameToShopDto.quantity(), gameToShopDto.price());
        return gameOfferRepository.save(newGameOffer);
    }

    @Transactional
    public void removeGameFromShop(UUID gameOfferId) {
        GameOffer gameOffer = gameOfferRepository.findById(gameOfferId).orElseThrow(GameNotFoundException::new);
        gameOfferRepository.delete(gameOffer);
    }

    @Transactional(readOnly = true)
    public List<ShopWithoutOfferDto> findShopsByGameDeveloper(String developer) {
        return gameOfferRepository.findShopsByGameDeveloper(developer).stream().map(gameOffer -> new ShopWithoutOfferDto(gameOffer.getId(), gameOffer.getName(), gameOffer.getAddress(), gameOffer.getPhoneNumber(), gameOffer.getEstablishedDate(), gameOffer.getOpeningTime(), gameOffer.getClosingTime())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Game> findAllGamesFromShopOrderedByPrice(UUID shopId) {
        return gameOfferRepository.findAllGamesFromShopOrderedByPrice(shopId);
    }

    @Transactional(readOnly = true)
    public GameOffer findTopByGame_IdOrderByPriceAsc(UUID gameId) {
        return gameOfferRepository.findTopByGame_IdOrderByPriceAsc(gameId);
    }

    @Transactional(readOnly = true)
    public List<GameOfferDto> findAllByGame_Title(String title) {
        return gameOfferRepository.findAllByGame_Title(title);
    }
}
