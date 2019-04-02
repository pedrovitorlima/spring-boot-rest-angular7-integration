package br.pedro.sandbox.springandangular.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("SECURITY_MOCK")
public class ProductRESTControllerTest {

	@Autowired
	private MockMvc mvc;
	
	
	@Test
	public void testGetProductsShouldReturnSomething() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/products")).andReturn();
		assertNotNull(result.getResponse().getContentAsString());
	}

	
//	@Before
//	public void authenticate() throws Exception {
//		Credentials credentials = new Credentials("bruce.dickinson", "bruce.dickinson");
//		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/auth")
//				.header("Origin", "*")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(new ObjectMapper().writeValueAsString(credentials))).andReturn();
//
//		this.authToken = result.getResponse().getHeader("Authorization");
//	}
//	
//	@Test
//	public void testIsAuthenticated() {
//		assertNotNull(this.authToken);
//	}

}
