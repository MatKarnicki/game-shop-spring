package ug.edu.game.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GameToShopDto(@NotNull UUID gameId, @NotNull Integer quantity, @NotNull Double price) {

}
