package ug.edu.game.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ug.edu.game.rest.domain.GameShop;
import ug.edu.game.rest.dto.ShopRevenueDto;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface GameShopRepository extends JpaRepository<GameShop, UUID> {

    List<GameShop> findAllByAddressContainingIgnoreCase(String address);

    @Query("SELECT gs FROM GameShop gs WHERE :currentTime BETWEEN gs.openingTime AND gs.closingTime")
    List<GameShop> findAllCurrentlyOpen(@Param("currentTime") LocalTime currentTime);

    @Query("SELECT new ug.edu.game.rest.dto.ShopRevenueDto(" +
            "go.gameShop.id, " +
            "SUM(go.price * go.quantity), " +
            "COUNT(DISTINCT go.game.id)) " +
            "FROM GameOffer go " +
            "WHERE go.gameShop.id = :shopId " +
            "GROUP BY go.gameShop.id")
    ShopRevenueDto calculateExpectedRevenueForShop(@Param("shopId") UUID shopId);

}