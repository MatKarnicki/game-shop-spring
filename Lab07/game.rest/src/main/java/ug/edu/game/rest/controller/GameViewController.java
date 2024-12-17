package ug.edu.game.rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ug.edu.game.rest.domain.Game;
import ug.edu.game.rest.service.GameService;
import ug.edu.game.rest.exception.GameNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/games")
public class GameViewController {

    private final GameService gameService;

    @Autowired
    public GameViewController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String getAllGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "games/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("game", new Game("Placeholder", "Placeholder", LocalDate.now(), 0));
        return "games/add";
    }

    @PostMapping("/add")
    public String addGame(@Valid @ModelAttribute Game game, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "games/add";
        }
        gameService.addGame(game);
        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        try {
            Game game = gameService.getGameById(id);
            model.addAttribute("game", game);
            return "games/edit";
        } catch (GameNotFoundException e) {
            model.addAttribute("error", "Game not found.");
            return "redirect:/games";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateGame(@PathVariable UUID id, @Valid @ModelAttribute Game updatedGame, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("game", updatedGame);
            return "games/edit";
        }
        try {
            gameService.updateGame(id, updatedGame);
            return "redirect:/games";
        } catch (GameNotFoundException e) {
            model.addAttribute("error", "Game not found.");
            return "redirect:/games";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable UUID id) {
        try {
            gameService.deleteGame(id);
        } catch (GameNotFoundException e) {
            return "redirect:/games";
        }
        return "redirect:/games";
    }
}
