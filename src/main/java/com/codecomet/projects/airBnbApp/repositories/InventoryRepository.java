package com.codecomet.projects.airBnbApp.repositories;

import com.codecomet.projects.airBnbApp.dto.HotelDto;
import com.codecomet.projects.airBnbApp.entity.Hotel;
import com.codecomet.projects.airBnbApp.entity.Inventory;
import com.codecomet.projects.airBnbApp.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    void deleteByRoom(Room room);

    @Query(value = """    
            SELECT *
            FROM hotel
            WHERE id IN (
                    SELECT h1_0.id
                            FROM inventory i1_0
                            JOIN hotel h1_0 ON h1_0.id = i1_0.hotel_id
                            WHERE i1_0.city = :city
                            AND i1_0.inventory_date BETWEEN :startDate AND :endDate
                            AND i1_0.closed = 0
                            AND (i1_0.total_count - i1_0.booked_count - i1_0.reserved_count) >= :roomsCount
            GROUP BY h1_0.id
            HAVING COUNT(DISTINCT i1_0.inventory_date) = :dateCount
            )
            """,nativeQuery = true)
    List<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") Long dateCount
    );

    @Query(value = """
             SELECT i FROM Inventory i
             WHERE i.room.id = :roomId
               AND i.inventoryDate BETWEEN :startDate AND :endDate
               AND i.closed = false
               AND (i.totalCount - i.bookedCount - i.reservedCount) >= :roomsCount
            """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    /* this will lock the entries for some time until the transactional method ends*/
    List<Inventory> findAndLockAvailableInventory(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount
    );

    List<Inventory> findByHotelAndInventoryDateBetween(Hotel hotel, LocalDate startDate, LocalDate endDate);
}
