package ug.edu.game.rest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ug.edu.game.rest.domain.GameFranchise;
import ug.edu.game.rest.dto.FranchiseGameCountDto;
import ug.edu.game.rest.dto.FranchisePerShopDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface GameFranchiseRepository extends JpaRepository<GameFranchise, UUID> {
    GameFranchise findByName(String name);

    List<GameFranchise> findByLastReleaseDateAfter(LocalDate date);

    @Query("SELECT new ug.edu.game.rest.dto.FranchisePerShopDto(" +
            "gs.id, " +
            "gs.name, " +
            "gs.address, " +
            "COUNT(go.game)) " +
            "FROM GameShop gs " +
            "JOIN GameOffer go ON gs.id = go.gameShop.id " +
            "JOIN Game g ON go.game.id = g.id " +
            "JOIN GameFranchise gf ON g.franchise.id = gf.id " +
            "WHERE gf.name = :franchise " +
            "GROUP BY gs.id")
    List<FranchisePerShopDto> findShopsWithGameCountFromFranchise(@Param("franchise") String franchise);

    @Query("SELECT new ug.edu.game.rest.dto.FranchiseGameCountDto(gf.id, gf.name, COUNT(g)) " +
            "FROM GameFranchise gf " +
            "LEFT JOIN Game g ON g.franchise = gf " +
            "GROUP BY gf.id, gf.name")
    List<FranchiseGameCountDto> findAllFranchisesWithGameCount();
}
