package ug.edu.game.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ug.edu.game.rest.domain.Game;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
    @Query("SELECT g FROM Game g WHERE g.franchise IS NULL OR g.franchise.id <> :franchiseId")
    List<Game> findAllGamesNotInFranchise(@Param("franchiseId") UUID franchiseId);

    List<Game> findByFranchise_Id(UUID franchiseId);

    List<Game> findAllByGameDetailsDeveloperEqualsIgnoreCase(String developer);

    List<Game> findAllByReleaseDateIsAfterAndReleaseDateBefore(LocalDate releaseDateFloor, LocalDate releaseDateCeiling);
}