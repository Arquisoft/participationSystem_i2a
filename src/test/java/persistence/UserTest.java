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
		
		User user = new User("12345678A", "Pepe", "Calleja", simpleDate, "Oviedo", "calleja@email.com", "Español", 2);
		user.setId(1);
		user.setPassword("password");
		
		User found = dao.getUserById(1);
		System.out.println(found);
		System.out.println(user);
		Assert.assertEquals(user, found);
	}

}
