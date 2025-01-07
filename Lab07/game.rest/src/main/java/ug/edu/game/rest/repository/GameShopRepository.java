package ug.edu.game.rest.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ug.edu.game.rest.domain.Game;

import java.util.UUID;

public interface GameShopRepository extends JpaRepository<Game, UUID> {
}