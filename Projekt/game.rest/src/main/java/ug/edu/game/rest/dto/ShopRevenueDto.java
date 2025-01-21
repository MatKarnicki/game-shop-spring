package ug.edu.game.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ShopRevenueDto(@NotNull UUID shopId, @NotNull Double revenue, @NotNull Long numberOfGames) {
}
