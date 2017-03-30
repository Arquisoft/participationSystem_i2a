package persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import dto.User;

public class UserTest {
	UserDao dao = Persistence.getUserDao();

	@Test
	public void testFindById() throws ParseException {
		Date simpleDate = new SimpleDateFormat("dd/MM/yyyy").parse("25/03/1950");
		
		User user = new User().setId(1).setDni("12345678A").setName("Pepe").setSurname("Calleja").setEmail("calleja@email.com")
				.setBirthdate(simpleDate).setNationality("Español").setAddress("Oviedo").setPollingStation(2)
				.setPassword("password");
		
		User found = dao.getUserById(1);
		System.out.println(found);
		System.out.println(user);
		Assert.assertEquals(user, found);
	}

}
