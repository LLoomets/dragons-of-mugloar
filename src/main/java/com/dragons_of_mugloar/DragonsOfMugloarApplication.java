package com.dragons_of_mugloar;

import com.dragons_of_mugloar.service.GameLoopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DragonsOfMugloarApplication implements CommandLineRunner {

    @Autowired
    private GameLoopService gameLoopService;

	public static void main(String[] args) {
		SpringApplication.run(DragonsOfMugloarApplication.class, args);
	}

    @Override
    public void run(String... args) {
        gameLoopService.playGame();
    }

}
