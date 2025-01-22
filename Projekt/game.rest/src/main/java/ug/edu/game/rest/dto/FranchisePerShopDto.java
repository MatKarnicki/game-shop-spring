package ug.edu.game.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FranchisePerShopDto(@NotNull UUID shopId, @NotNull String shopName, @NotNull String shopAddress,
                                  @NotNull Long gameCount) {

}
