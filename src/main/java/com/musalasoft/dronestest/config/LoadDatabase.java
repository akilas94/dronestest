package com.musalasoft.dronestest.config;

import com.musalasoft.dronestest.entity.Drone;
import com.musalasoft.dronestest.enums.Model;
import com.musalasoft.dronestest.enums.State;
import com.musalasoft.dronestest.repository.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(DroneRepository droneRepository) {

        return args -> {
            log.info("Preloading " + droneRepository.save(new Drone("00001", Model.Cruiserweight,
                    200, 100, State.IDLE)));
            log.info("Preloading " + droneRepository.save(new Drone("00002", Model.Lightweight,
                    100, 100, State.IDLE)));
            log.info("Preloading " + droneRepository.save(new Drone("00003", Model.Middleweight,
                    300, 100, State.IDLE)));
            log.info("Preloading " + droneRepository.save(new Drone("00004", Model.Heavyweight,
                    500, 100, State.IDLE)));
            log.info("Preloading " + droneRepository.save(new Drone("00005", Model.Cruiserweight,
                    200, 67, State.IDLE)));
            log.info("Preloading " + droneRepository.save(new Drone("00006", Model.Middleweight,
                    250, 100, State.IDLE)));
            log.info("Preloading " + droneRepository.save(new Drone("00007", Model.Middleweight,
                    250, 100, State.IDLE)));
            log.info("Preloading " + droneRepository.save(new Drone("00008", Model.Lightweight,
                    140, 100, State.IDLE)));
            log.info("Preloading " + droneRepository.save(new Drone("00009", Model.Cruiserweight,
                    220, 100, State.IDLE)));
            log.info("Preloading " + droneRepository.save(new Drone("00010", Model.Heavyweight,
                    450, 100, State.IDLE)));
        };
    }
}
