package ug.edu.game.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ug.edu.game.rest.domain.GameOffer;

import java.util.UUID;

public interface GameOfferRepository extends JpaRepository<GameOffer, UUID> {
}