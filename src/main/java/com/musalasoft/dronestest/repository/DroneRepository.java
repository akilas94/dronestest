package com.musalasoft.dronestest.repository;

import com.musalasoft.dronestest.entity.Drone;
import com.musalasoft.dronestest.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    @Modifying
    @Query(value = "UPDATE drone SET state =?2 WHERE id=?1", nativeQuery = true)
    int updateStatusByDroneId(Long id, String state);

    @Modifying
    @Query(value = "UPDATE drone SET batter_capacity =?2 WHERE id=?1", nativeQuery = true)
    int updateBatteryByDroneId(Long id, double batterCapacity);

    Optional<Drone> findBySerialNumber(String serialNumber);
}
