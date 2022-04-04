package com.hackathon.avengers;

import com.hackathon.avengers.client.UserClient;
import com.hackathon.avengers.dto.IdDTO;
import com.hackathon.avengers.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.hackathon.avengers:provider:+:stubs:6565",
				stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class UserClientTest {

	@Autowired
	private UserClient userClient;

	@Test
	public void createUserCompliesToContract() {
		UserDTO user = new UserDTO();
		user.setFirstName("Arthur");
		user.setLastName("Dent");
		IdDTO id = userClient.createUser(user);
		assertThat(id.getId()).isEqualTo(42L);
	}

	@Test
	public void updateUserCompliesToContract() {
		UserDTO user = new UserDTO();
		user.setFirstName("Arthur");
		user.setLastName("Dent");
		UserDTO res = userClient.updateUser(42L, user);

		assertThat(res.getId()).isEqualTo(42L);
	}

	@Test
	public void getUserCompliesToContract() {
		UserDTO res = userClient.getUser(42L);

		assertThat(res.getId()).isEqualTo(42L);
		assertThat(res.getFirstName()).isEqualTo("Arthur");
		assertThat(res.getLastName()).isEqualTo("Dent");
	}

}
