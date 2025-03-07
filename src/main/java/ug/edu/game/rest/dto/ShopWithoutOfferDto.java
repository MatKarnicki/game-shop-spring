package ug.edu.game.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ShopWithoutOfferDto(@NotNull UUID shopId, @NotNull String name, @NotNull String address,
                                  @NotNull String phoneNumber, @NotNull LocalDate establishedDate,
                                  @NotNull LocalTime openingTime,
                                  @NotNull LocalTime closingTime) {
}


