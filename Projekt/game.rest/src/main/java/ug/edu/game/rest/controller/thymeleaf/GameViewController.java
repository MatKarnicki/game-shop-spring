package ug.edu.game.rest.controller.thymeleaf;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.domain.GameOffer;
import ug.edu.game.rest.dto.GameOfferDto;
import ug.edu.game.rest.service.GameOfferService;
import ug.edu.game.rest.service.GameService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/games")
public class GameViewController {

    private final GameService gameService;
    private final GameOfferService gameOfferService;


    @Autowired
    public GameViewController(
            GameService gameService,
            GameOfferService gameOfferService) {
        this.gameService = gameService;
        this.gameOfferService = gameOfferService;

    }

    @GetMapping()
    public String listGames(
            @RequestParam(defaultValue = "title") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        // Fetch sorted and paginated list of games
        Pageable pageable = PageRequest.of(page, 10,
                order.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());

        Page<Game> gamesPage = gameService.getPaginatedGames(pageable);

        model.addAttribute("games", gamesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", gamesPage.getTotalPages());
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);

        return "games/list"; // Return the Thymeleaf template
    }


    @GetMapping("/new")
    public String showGameForm(Model model) {
        model.addAttribute("game", new Game());
        return "games/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditGameForm(@PathVariable UUID id, Model model) {
        Game game = gameService.getGameById(id);
        model.addAttribute("game", game);
        return "games/form";
    }

    @PostMapping("/save")
    public String saveGame(
            @Valid @ModelAttribute("game") Game game,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "games/form";
        }

        Game savedGame = game.getId() == null
                ? gameService.addGame(game)
                : gameService.updateGame(game.getId(), game);

        redirectAttributes.addFlashAttribute("successMessage",
                "Game " + savedGame.getTitle() + " saved successfully!");
        return "redirect:/games";
    }

    @GetMapping("/details/{id}")
    public String showGameDetails(@PathVariable UUID id, Model model) {
        Game game = gameService.getGameById(id);

        List<GameOfferDto> gameOffers = gameOfferService.findAllByGame_Title(game.getTitle());

        GameOffer lowestPriceOffer = gameOfferService.findTopByGame_IdOrderByPriceAsc(id);

        model.addAttribute("game", game);
        model.addAttribute("gameOffers", gameOffers);
        model.addAttribute("lowestPriceOffer", lowestPriceOffer);

        if (game.getFranchise() != null) {
            model.addAttribute("franchiseGames",
                    gameService.findByFranchise_Id(game.getFranchise().getId()));
        }

        return "games/details";
    }

    @PostMapping("/delete/{id}")
    public String deleteGame(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes
    ) {
        gameService.deleteGame(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Game deleted successfully!");
        return "redirect:/games";
    }
}