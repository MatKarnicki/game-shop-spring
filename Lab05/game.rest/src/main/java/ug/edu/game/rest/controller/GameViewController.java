package ug.edu.game.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ug.edu.game.rest.model.Game;
import ug.edu.game.rest.service.GameService;
import ug.edu.game.rest.exception.GameNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public String addGame(@ModelAttribute Game game) {
        gameService.addGame(game);
        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        try {
            Game game = gameService.getGameById(id);
            model.addAttribute("game", game);
            return "games/edit";
        } catch (GameNotFoundException e) {
            return "redirect:/games";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateGame(@PathVariable String id, @ModelAttribute Game updatedGame) {
        try {
            gameService.updateGame(id, updatedGame);
            return "redirect:/games";
        } catch (GameNotFoundException e) {
            return "redirect:/games";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable String id) {
        try {
            gameService.deleteGame(id);
        } catch (GameNotFoundException e) {
            return "redirect:/games";
        }
        return "redirect:/games";
    }
}
