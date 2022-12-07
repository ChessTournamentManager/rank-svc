package com.chesstournamentmanager.roundsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RankSvcApplication {

	@GetMapping("/message")
	public String getMessage() {
		return "Welcome to the round service.";
	}

	public static void main(String[] args) {
		SpringApplication.run(RankSvcApplication.class, args);
	}

}
