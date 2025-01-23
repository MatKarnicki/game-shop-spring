package ug.edu.game.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ug.edu.game.rest.domain.Game;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
    List<Game> findByFranchise_Id(UUID franchiseId);
    
    List<Game> findAllByGameDetailsDeveloperEqualsIgnoreCase(String developer);

    List<Game> findAllByReleaseDateIsAfterAndReleaseDateBefore(LocalDate releaseDateFloor, LocalDate releaseDateCeiling);
}