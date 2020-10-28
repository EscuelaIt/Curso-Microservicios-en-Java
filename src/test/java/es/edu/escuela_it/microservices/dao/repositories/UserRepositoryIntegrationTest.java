package es.edu.escuela_it.microservices.dao.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.edu.escuela_it.microservices.dao.entities.UserEntity;

@Disabled
@DataJpaTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private UserRepository userRepostory;

	@Test
	public void WhenFindById_thenReturnTeam() {

		UserEntity team = new UserEntity(1, "Rafael");
		userRepostory.save(team);

		// AssertJ
		Assertions.assertThat(userRepostory.findById(1))
										.isNotEmpty()
										.hasValue(team);
	}

}


