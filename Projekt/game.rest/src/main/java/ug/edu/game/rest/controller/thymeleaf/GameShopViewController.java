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
import ug.edu.game.rest.domain.GameShop;
import ug.edu.game.rest.dto.GameToShopDto;
import ug.edu.game.rest.dto.ShopRevenueDto;
import ug.edu.game.rest.service.GameOfferService;
import ug.edu.game.rest.service.GameService;
import ug.edu.game.rest.service.GameShopService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/shops")
public class GameShopViewController {

    private final GameShopService gameShopService;
    private final GameOfferService gameOfferService;
    private final GameService gameService;

    @Autowired
    public GameShopViewController(GameShopService gameShopService, GameOfferService gameOfferService, GameService gameService) {
        this.gameShopService = gameShopService;
        this.gameOfferService = gameOfferService;
        this.gameService = gameService;
    }

    // List all shops with pagination and sorting
    @GetMapping()
    public String listShops(
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Pageable pageable = PageRequest.of(page, 10,
                order.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());

        Page<GameShop> shopsPage = gameShopService.getPaginatedShops(pageable);

        model.addAttribute("shops", shopsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", shopsPage.getTotalPages());
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);

        return "shops/list";
    }

    // Show form for adding a new shop
    @GetMapping("/new")
    public String showShopForm(Model model) {
        model.addAttribute("shop", new GameShop());
        return "shops/form";
    }

    // Show form for editing an existing shop
    @GetMapping("/edit/{id}")
    public String showEditShopForm(@PathVariable UUID id, Model model) {
        GameShop shop = gameShopService.getGameShopById(id);
        model.addAttribute("shop", shop);
        return "shops/form";
    }

    // Save or update a shop
    @PostMapping("/save")
    public String saveShop(
            @Valid @ModelAttribute("shop") GameShop shop,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "shops/form";
        }

        GameShop savedShop = shop.getId() == null
                ? gameShopService.addGameShop(shop)
                : gameShopService.updateGameShop(shop.getId(), shop);

        redirectAttributes.addFlashAttribute("successMessage",
                "Shop " + savedShop.getName() + " saved successfully!");
        return "redirect:/shops";
    }

    // Show shop details
    @GetMapping("/{id}")
    public String showShopDetails(@PathVariable UUID id, Model model) {
        GameShop shop = gameShopService.getGameShopById(id);
        ShopRevenueDto expectedRevenue = gameShopService.calculateExpectedRevenueForShop(id);
        List<GameOffer> gameOffers = shop.getGameOffers();
        List<Game> allGames = gameService.getAllGames();

        model.addAttribute("shop", shop);
        model.addAttribute("expectedRevenue", expectedRevenue.revenue());
        model.addAttribute("gameOffers", gameOffers);
        model.addAttribute("allGames", allGames);

        return "shops/details";
    }

    // Add a game to the shop
    @PostMapping("/{id}/add-game")
    public String addGameToShop(
            @PathVariable UUID id,
            @RequestParam UUID gameId,
            @RequestParam Integer quantity,
            @RequestParam Double price,
            RedirectAttributes redirectAttributes
    ) {
        GameToShopDto gameToShopDto = new GameToShopDto(gameId, quantity, price);
        gameOfferService.addGameToShop(id, gameToShopDto);

        redirectAttributes.addFlashAttribute("successMessage", "Game added to shop successfully!");
        return "redirect:/shops/" + id;
    }

    // Remove a game from the shop
    @PostMapping("/{shopId}/remove-game/{gameId}")
    public String removeGameFromShop(
            @PathVariable UUID shopId,
            @PathVariable UUID gameId,
            RedirectAttributes redirectAttributes
    ) {
        gameOfferService.removeGameFromShop(gameId);

        redirectAttributes.addFlashAttribute("successMessage", "Game removed from shop successfully!");
        return "redirect:/shops/" + shopId;
    }

    // Delete a shop
    @PostMapping("/delete/{id}")
    public String deleteShop(
            @PathVariable UUID id,
            RedirectAttributes redirectAttributes
    ) {
        gameShopService.deleteGameShop(id);
        redirectAttributes.addFlashAttribute("successMessage", "Shop deleted successfully!");
        return "redirect:/shops";
    }

    @GetMapping("offer/{offerId}/edit-offer")
    public String showEditOfferForm(@PathVariable UUID offerId, Model model) {
        GameOffer offer = gameOfferService.getGameOfferById(offerId);
        GameShop shop = offer.getGameShop();

        // Add data to the model
        model.addAttribute("offer", offer);
        model.addAttribute("shop", shop);

        return "shops/gameOfferForm"; // Name of the Thymeleaf template
    }

    @PostMapping("/offer/{offerId}/edit-offer")
    public String updateGameOffer(
            @PathVariable UUID offerId,
            @ModelAttribute("offer") GameOffer updatedOffer
    ) {
        GameOffer existingOffer = gameOfferService.getGameOfferById(offerId);
        gameOfferService.updateGameOffer(offerId, updatedOffer);
        return "redirect:/shops/" + existingOffer.getGameShop().getId();
    }

    @PostMapping("/offer/{offerId}/remove-game")
    public String deleteGameOffer(
            @PathVariable UUID offerId,
            @ModelAttribute("offer") GameOffer updatedOffer
    ) {
        UUID shopId = gameOfferService.getGameOfferById(offerId).getGameShop().getId();
        gameOfferService.removeGameFromShop(offerId);
        return "redirect:/shops/" + shopId;
    }
}
