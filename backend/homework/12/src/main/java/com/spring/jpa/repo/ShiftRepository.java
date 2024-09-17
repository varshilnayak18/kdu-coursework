package com.spring.jpa.repo;

import com.spring.jpa.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, UUID> {
    List<Shift> findAllByTenantId(UUID tenantId);
    @Query("SELECT s FROM Shift s WHERE s.dateStart = :dateStart AND s.dateEnd = :dateEnd ORDER BY s.name ASC LIMIT 3")
    List<Shift> findTop3ShiftsByDateRange(@Param("dateStart") LocalDate startDate, @Param("dateEnd") LocalDate endDate);
}
