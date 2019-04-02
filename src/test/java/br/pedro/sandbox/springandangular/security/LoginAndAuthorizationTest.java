package br.pedro.sandbox.springandangular.security;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.pedro.sandbox.springandangular.security.domain.Credentials;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginAndAuthorizationTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testRequestAuthLoginWithValidCredentialsShouldPutAuthorizationHeader() throws JsonProcessingException, Exception {
		Credentials credentials = new Credentials("bruce.dickinson", "bruce.dickinson");
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/auth")
				.header("Origin", "*")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(credentials))).andReturn();

		final String authToken = result.getResponse().getHeader("Authorization");
		assertTrue(authToken.contains("Bearer "));
	}

}
