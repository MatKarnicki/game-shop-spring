package ug.edu.game.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GameOfferDto(@NotNull UUID gameShopId, @NotNull String gameShopName, @NotNull String gameTitle,
                           @NotNull Double price) {
}
