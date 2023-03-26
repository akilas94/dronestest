package com.musalasoft.dronestest.repository;

import com.musalasoft.dronestest.entity.Medication;
import com.musalasoft.dronestest.entity.Orders;
import com.musalasoft.dronestest.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByDrone_IdAndDrone_State(Long droneId, State state);
}
