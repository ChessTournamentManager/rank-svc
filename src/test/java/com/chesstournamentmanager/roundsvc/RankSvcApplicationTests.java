package com.chesstournamentmanager.roundsvc;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RankSvcApplicationTests {

	@Autowired
	private RankSvcApplication rankSvcApplication;

	@Test
	void contextLoads() {
	}

	@Test
	void sendsWelcomeMessage() {
		assertThat(rankSvcApplication.getMessage()).isEqualTo("Welcome to the round service.");
	}
}
