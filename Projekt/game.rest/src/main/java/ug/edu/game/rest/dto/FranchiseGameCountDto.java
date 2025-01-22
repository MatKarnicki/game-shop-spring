package ug.edu.game.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FranchiseGameCountDto(@NotNull UUID franchiseId, @NotNull String franchiseName, @NotNull long gameCount) {
}
