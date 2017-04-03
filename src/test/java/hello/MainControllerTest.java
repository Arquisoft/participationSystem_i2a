package hello;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class MainControllerTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}

	@Test
	public void getLanding() throws Exception {
		URI userURI = new URI(base.toString() + "/user");
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), containsString("Login"));
	}

	@Test
	public void getUser() throws Exception {
		String userURI = base.toString() + "/user";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		UserInfo expected = new UserInfo("pepe", 0);
	}

	@Test
	public void getUserHome() throws Exception {
		String userURI = base.toString() + "/user/home";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Participation System"));
	}

	@Test
	public void getAdmin() throws Exception {
		String userURI = base.toString() + "/admin";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Administrator"));
	}

	@Test
	public void getAddForm() throws Exception {
		String userURI = base.toString() + "/user/addForm";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("Add your proposal"));
	}

	@Test
	public void getMensajeError() throws Exception {
		String userURI = base.toString() + "/mensajeError";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		assertThat(response.getBody(), containsString("DON'T USE BAD WORDS!!!"));

	}
}