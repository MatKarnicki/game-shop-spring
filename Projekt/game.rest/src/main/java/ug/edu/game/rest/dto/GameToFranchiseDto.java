package ug.edu.game.rest.dto;


import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GameToFranchiseDto(@NotNull UUID gameId) {

}
