package com.musalasoft.dronestest.repository;

import com.musalasoft.dronestest.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Optional<Medication> findByNameAndCode(String name, String code);

    @Query(value = "select sum(weight)  from medication m  where m.id  in (?1)", nativeQuery = true)
    Double findWeightOfAllItems(List<Long> ids);
}
