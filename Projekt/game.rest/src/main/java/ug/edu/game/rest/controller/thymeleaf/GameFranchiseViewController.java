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
import ug.edu.game.rest.domain.GameFranchise;
import ug.edu.game.rest.dto.FranchisePerShopDto;
import ug.edu.game.rest.service.GameFranchiseService;
import ug.edu.game.rest.service.GameService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/franchises")
public class GameFranchiseViewController {

    private final GameFranchiseService gameFranchiseService;
    private final GameService gameService;

    @Autowired
    public GameFranchiseViewController(GameFranchiseService gameFranchiseService, GameService gameService) {
        this.gameFranchiseService = gameFranchiseService;
        this.gameService = gameService;
    }

    @GetMapping()
    public String listFranchises(
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10,
                order.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());

        Page<GameFranchise> franchisesPage = gameFranchiseService.getPaginatedFranchises(pageable);

        model.addAttribute("franchises", franchisesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", franchisesPage.getTotalPages());
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);

        return "franchises/list";
    }

    @GetMapping("/new")
    public String showFranchiseForm(Model model) {
        model.addAttribute("franchise", new GameFranchise());
        return "franchises/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditFranchiseForm(@PathVariable UUID id, Model model) {
        GameFranchise franchise = gameFranchiseService.getGameFranchiseById(id);
        model.addAttribute("franchise", franchise);
        return "franchises/form";
    }

    @PostMapping("/save")
    public String saveFranchise(
            @Valid @ModelAttribute("franchise") GameFranchise franchise,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "franchises/form";
        }

        GameFranchise savedFranchise = franchise.getId() == null
                ? gameFranchiseService.addGameFranchise(franchise)
                : gameFranchiseService.updateGameFranchise(franchise.getId(), franchise);

        redirectAttributes.addFlashAttribute("successMessage",
                "Franchise " + savedFranchise.getName() + " saved successfully!");
        return "redirect:/franchises";
    }

    @GetMapping("/{id}")
    public String showFranchiseDetails(@PathVariable UUID id, Model model) {
        GameFranchise franchise = gameFranchiseService.getGameFranchiseById(id);
        List<Game> franchiseGames = franchise.getGames();
        List<FranchisePerShopDto> franchiseShops = gameFranchiseService.findShopsWithGameCountFromFranchise(franchise.getName());

        List<Game> allGamesNotInFranchise = gameService.getAllGamesNotInFranchise(id);

        model.addAttribute("franchise", franchise);
        model.addAttribute("franchiseGames", franchiseGames);
        model.addAttribute("franchiseShops", franchiseShops);
        model.addAttribute("allGames", allGamesNotInFranchise); // Pass games not in the franchise to the template

        return "franchises/details";
    }

    @PostMapping("/{franchiseId}/remove-game/{gameId}")
    public String removeGameFromFranchise(
            @PathVariable UUID franchiseId,
            @PathVariable UUID gameId,
            RedirectAttributes redirectAttributes
    ) {
        gameFranchiseService.removeGameFromFranchise(franchiseId, gameId);
        redirectAttributes.addFlashAttribute("successMessage", "Game removed from franchise successfully!");
        return "redirect:/franchises/" + franchiseId;
    }

    @PostMapping("/{id}/add-game")
    public String addGameToFranchise(
            @PathVariable UUID id,
            @RequestParam UUID gameId,
            RedirectAttributes redirectAttributes
    ) {
        gameFranchiseService.addGameToFranchise(id, gameId);
        redirectAttributes.addFlashAttribute("successMessage", "Game added to franchise successfully!");
        return "redirect:/franchises/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteFranchise(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes
    ) {
        gameFranchiseService.deleteGameFranchise(id);
        redirectAttributes.addFlashAttribute("successMessage", "Franchise deleted successfully!");
        return "redirect:/franchises";
    }
}