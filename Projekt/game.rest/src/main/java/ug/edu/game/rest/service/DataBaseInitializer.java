package ug.edu.game.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ug.edu.game.rest.domain.*;
import ug.edu.game.rest.repository.GameFranchiseRepository;
import ug.edu.game.rest.repository.GameOfferRepository;
import ug.edu.game.rest.repository.GameRepository;
import ug.edu.game.rest.repository.GameShopRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DataBaseInitializer {

    private final GameRepository gameRepository;
    private final GameShopRepository gameShopRepository;
    private final GameFranchiseRepository gameFranchiseRepository;
    private final GameOfferRepository gameOfferRepository;

    public DataBaseInitializer(GameRepository gameRepository,
                               GameShopRepository gameShopRepository,
                               GameFranchiseRepository gameFranchiseRepository,
                               GameOfferRepository gameOfferRepository) {
        this.gameRepository = gameRepository;
        this.gameShopRepository = gameShopRepository;
        this.gameFranchiseRepository = gameFranchiseRepository;
        this.gameOfferRepository = gameOfferRepository;
    }

    @Transactional
    public void initializeDatabase() {
        gameFranchiseRepository.saveAll(Arrays.asList(
                new GameFranchise("Souls Series", "FromSoftware", 75000000f),
                new GameFranchise("Monster Hunter", "Capcom", 27000000f),
                new GameFranchise("Final Fantasy", "Square Enix", 200000000f),
                new GameFranchise("Wiedźmin", "CD Projekt Red", 52000000f),
                new GameFranchise("Devil May Cry", "Capcom", 9000000f),
                new GameFranchise("Rocket League", "Psyonix", 10000000f),
                new GameFranchise("Stardew Valley", "ConcernedApe", 20000000f),
                new GameFranchise("Animal Crossing", "Nintendo", 100000000f),
                new GameFranchise("Overwatch", "Blizzard Entertainment", 50000000f)
        ));
        gameShopRepository.saveAll(Arrays.asList(
                new GameShop("GameHub", "Przykładów, Przykładówny 6", "+1234567890", LocalTime.of(9, 0), LocalTime.of(21, 0), LocalDate.of(2010, 5, 20)),
                new GameShop("Virtual Paradise", "Gdańsk, WitkaStwosza 210", "+9876543210", LocalTime.of(10, 0), LocalTime.of(22, 0), LocalDate.of(2015, 8, 15)),
                new GameShop("Retro Realm", "Sopot, Recenzyjna 1", "+1122334455", LocalTime.of(8, 30), LocalTime.of(20, 30), LocalDate.of(2005, 11, 10)),
                new GameShop("Virtual Vault", "Gdynia, Kościuszki 55", "+9988776655", LocalTime.of(11, 0), LocalTime.of(23, 0), LocalDate.of(2020, 1, 1)),
                new GameShop("Game Vault", "Gdańsk, Kościuszki 66", "+2233445566", LocalTime.of(9, 30), LocalTime.of(21, 30), LocalDate.of(2000, 4, 5))
        ));
        gameRepository.saveAll(Arrays.asList(
                new Game("Bloodborne", "Souls-like", LocalDate.of(2015, 10, 11), 10000000),
                new Game("Monster Hunter World", "RPG", LocalDate.of(2018, 2, 15), 15000000),
                new Game("Final Fantasy XVI", "RPG", LocalDate.of(2023, 6, 3), 3000000),
                new Game("Wiedźmin 3: Dziki Gon", "RPG", LocalDate.of(2015, 5, 19), 50000000),
                new Game("Wiedźmin 2: Zabójca Królów", "RPG", LocalDate.of(2011, 5, 17), 2000000),
                new Game("Devil May Cry 5", "Character-Action", LocalDate.of(2019, 3, 8), 6000000),
                new Game("Devil May Cry 4", "Character-Action", LocalDate.of(2008, 1, 31), 3000000),
                new Game("Dark Souls III", "Souls-like", LocalDate.of(2016, 4, 12), 10000000),
                new Game("Dark Souls II", "Souls-like", LocalDate.of(2014, 3, 11), 2500000),
                new Game("Final Fantasy XV", "RPG", LocalDate.of(2016, 11, 29), 10000000),
                new Game("Rocket League", "Sports", LocalDate.of(2015, 7, 7), 10000000),
                new Game("Stardew Valley", "Simulation", LocalDate.of(2016, 2, 26), 20000000),
                new Game("Animal Crossing: New Horizons", "Simulation", LocalDate.of(2020, 3, 20), 42000000),
                new Game("Overwatch", "Shooter", LocalDate.of(2016, 5, 24), 50000000)
        ));
        for (Game game : gameRepository.findAll()) {
            switch (game.getTitle()) {
                case "Bloodborne":
                    game.setFranchise(gameFranchiseRepository.findByName("Souls Series"));
                    game.setGameDetails(new GameDetails("FromSoftware", LocalDate.of(1986, 11, 15), "Japan", "Action role-playing game with a gothic setting."));
                    break;
                case "Monster Hunter World":
                    game.setFranchise(gameFranchiseRepository.findByName("Monster Hunter"));
                    game.setGameDetails(new GameDetails("Capcom", LocalDate.of(1983, 6, 11), "Japan", "RPG game where players hunt gigantic monsters."));
                    break;
                case "Final Fantasy XVI":
                    game.setFranchise(gameFranchiseRepository.findByName("Final Fantasy"));
                    game.setGameDetails(new GameDetails("Square Enix", LocalDate.of(1986, 5, 22), "Japan", "Fantasy RPG set in a medieval-style world."));
                    break;
                case "Wiedźmin 3: Dziki Gon":
                    game.setFranchise(gameFranchiseRepository.findByName("Wiedźmin"));
                    game.setGameDetails(new GameDetails("CD Projekt Red", LocalDate.of(2002, 5, 1), "Poland", "An RPG set in a dark fantasy world."));
                    break;
                case "Wiedźmin 2: Zabójca Królów":
                    game.setFranchise(gameFranchiseRepository.findByName("Wiedźmin"));
                    game.setGameDetails(new GameDetails("CD Projekt Red", LocalDate.of(2002, 5, 1), "Poland", "The second game in the Witcher franchise."));
                    break;
                case "Devil May Cry 5":
                    game.setFranchise(gameFranchiseRepository.findByName("Devil May Cry"));
                    game.setGameDetails(new GameDetails("Capcom", LocalDate.of(1983, 6, 11), "Japan", "An action game with fast-paced combat and combos."));
                    break;
                case "Devil May Cry 4":
                    game.setFranchise(gameFranchiseRepository.findByName("Devil May Cry"));
                    game.setGameDetails(new GameDetails("Capcom", LocalDate.of(1983, 6, 11), "Japan", "An action game with devil hunting gameplay."));
                    break;
                case "Dark Souls III":
                    game.setFranchise(gameFranchiseRepository.findByName("Souls Series"));
                    game.setGameDetails(new GameDetails("FromSoftware", LocalDate.of(1986, 11, 15), "Japan", "Challenging action RPG set in a dark fantasy world."));
                    break;
                case "Dark Souls II":
                    game.setFranchise(gameFranchiseRepository.findByName("Souls Series"));
                    game.setGameDetails(new GameDetails("FromSoftware", LocalDate.of(1986, 11, 15), "Japan", "Second installment in the Souls series."));
                    break;
                case "Final Fantasy XV":
                    game.setFranchise(gameFranchiseRepository.findByName("Final Fantasy"));
                    game.setGameDetails(new GameDetails("Square Enix", LocalDate.of(1986, 5, 22), "Japan", "An open-world RPG set in a fantastical world."));
                    break;
                case "Rocket League":
                    game.setFranchise(gameFranchiseRepository.findByName("Rocket League"));
                    game.setGameDetails(new GameDetails("Psyonix", LocalDate.of(2000, 7, 4), "USA", "A soccer game with rocket-powered cars."));
                    break;
                case "Stardew Valley":
                    game.setFranchise(gameFranchiseRepository.findByName("Stardew Valley"));
                    game.setGameDetails(new GameDetails("ConcernedApe", LocalDate.of(2009, 5, 5), "USA", "A farming simulation game."));
                    break;
                case "Animal Crossing: New Horizons":
                    game.setFranchise(gameFranchiseRepository.findByName("Animal Crossing"));
                    game.setGameDetails(new GameDetails("Nintendo", LocalDate.of(1989, 8, 25), "Japan", "Life simulation game where you live on an island."));
                    break;
                case "Overwatch":
                    game.setFranchise(gameFranchiseRepository.findByName("Overwatch"));
                    game.setGameDetails(new GameDetails("Blizzard Entertainment", LocalDate.of(1991, 2, 8), "USA", "A team-based multiplayer first-person shooter."));
                    break;
            }
            gameRepository.save(game);
        }
        createOffersForGames(gameRepository.findAll(), gameShopRepository.findAll());
    }


    private void createOffersForGames(List<Game> games, List<GameShop> gameShops) {
        for (Game game : games) {
            Integer shopIndex = 0;
            for (int i = 0; i < 2; i++) {
                GameShop shop = gameShops.get(shopIndex % gameShops.size());
                GameOffer offer = new GameOffer(shop, game, 10 + i * 5, 30.0 + (int) (Math.random() * 20));
                gameOfferRepository.save(offer);
                shopIndex++;
            }
        }
    }
}
