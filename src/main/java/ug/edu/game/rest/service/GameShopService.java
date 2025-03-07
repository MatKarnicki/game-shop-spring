package ug.edu.game.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.GameShop;
import ug.edu.game.rest.dto.ShopRevenueDto;
import ug.edu.game.rest.dto.ShopWithoutOfferDto;
import ug.edu.game.rest.exception.GameShopNotFoundException;
import ug.edu.game.rest.repository.GameOfferRepository;
import ug.edu.game.rest.repository.GameRepository;
import ug.edu.game.rest.repository.GameShopRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameShopService {
    private final GameShopRepository gameShopRepository;
    private final GameOfferRepository gameOfferRepository;
    private final GameRepository gameRepository;
    private final GameService gameService;

    @Autowired
    public GameShopService(GameShopRepository gameShopRepository, GameOfferRepository gameOfferRepository, GameRepository gameRepository, GameService gameService) {
        this.gameShopRepository = gameShopRepository;
        this.gameOfferRepository = gameOfferRepository;
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }

    @Transactional(readOnly = true)
    public List<GameShop> getAllGameShops() {
        return gameShopRepository.findAll();
    }

    @Transactional(readOnly = true)
    public GameShop getGameShopById(UUID id) {
        return gameShopRepository.findById(id).orElseThrow(GameShopNotFoundException::new);
    }

    @Transactional
    public GameShop addGameShop(GameShop gameShop) {
        return gameShopRepository.save(gameShop);
    }

    @Transactional
    public GameShop updateGameShop(UUID id, GameShop updatedGameShop) {
        GameShop existingGameShop = gameShopRepository.findById(id).orElseThrow(GameShopNotFoundException::new);
        updatedGameShop.setId(existingGameShop.getId());
        updatedGameShop.setGameOffers(existingGameShop.getGameOffers());
        return gameShopRepository.save(updatedGameShop);
    }

    @Transactional
    public void deleteGameShop(UUID id) {
        GameShop gameShop = gameShopRepository.findById(id).orElseThrow(GameShopNotFoundException::new);
        gameShopRepository.delete(gameShop);
    }

    @Transactional(readOnly = true)
    public List<ShopWithoutOfferDto> findAllByAddressContainingIgnoreCase(String address) {
        return gameShopRepository.findAllByAddressContainingIgnoreCase(address).stream().map(gameShop -> new ShopWithoutOfferDto(gameShop.getId(), gameShop.getName(), gameShop.getAddress(), gameShop.getPhoneNumber(), gameShop.getEstablishedDate(), gameShop.getOpeningTime(), gameShop.getClosingTime())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ShopWithoutOfferDto> findAllCurrentlyOpen(LocalTime time) {
        return gameShopRepository.findAllCurrentlyOpen(time).stream().map(gameShop -> new ShopWithoutOfferDto(gameShop.getId(), gameShop.getName(), gameShop.getAddress(), gameShop.getPhoneNumber(), gameShop.getEstablishedDate(), gameShop.getOpeningTime(), gameShop.getClosingTime())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ShopRevenueDto calculateExpectedRevenueForShop(UUID shopId) {
        return gameShopRepository.calculateExpectedRevenueForShop(shopId);
    }

    @Transactional(readOnly = true)
    public Page<GameShop> getPaginatedShops(Pageable pageable) {
        return gameShopRepository.findAll(pageable);
    }

}
