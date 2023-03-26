package com.musalasoft.dronestest.service.impl;

import com.musalasoft.dronestest.entity.BatteryHst;
import com.musalasoft.dronestest.repository.BatteryHstRepository;
import com.musalasoft.dronestest.repository.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class BatteryHistoryServiceImpl {

    @Autowired
    private BatteryHstRepository batteryHstRepository;

    @Autowired
    private DroneRepository droneRepository;

    @Scheduled(cron = "${battery.hst.corn}")
    public void updateBatteryHst(){
        log.info("Battery History Process Start");
        droneRepository.findAll().forEach(drone -> {
            BatteryHst batteryHst = new BatteryHst();
            batteryHst.setBatteryCapacity(drone.getBatterCapacity());
            batteryHst.setDrone(drone);
            batteryHst.setLastUpdateDateTime(new Date());
            batteryHstRepository.save(batteryHst);

        });
        log.info("Battery History Process End");
    }
}
