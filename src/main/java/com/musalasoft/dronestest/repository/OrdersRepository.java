package com.musalasoft.dronestest.repository;

import com.musalasoft.dronestest.entity.Medication;
import com.musalasoft.dronestest.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
