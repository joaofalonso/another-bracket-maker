package com.alonso.abm;

import com.alonso.abm.controller.MatchController;
import com.alonso.abm.controller.PlayerController;
import com.alonso.abm.controller.TournamentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthApplicationTests {

	@Autowired
	private TournamentController tournamentController;

	@Autowired
	private PlayerController playerController;

	@Autowired
	private MatchController matchController;
	@Test
	void contextLoads() {
		assertThat(tournamentController).isNotNull();
		assertThat(playerController).isNotNull();
		assertThat(matchController).isNotNull();
	}

}
