package dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserDtoTest {
	User u;

	@Before
	public void setUp() throws Exception {
		u = new User();
	}

	@Test
	public void test() {
		assertNotNull(u);
		
		u.setId(new Integer(1));
		assertEquals(new Integer(1), u.getId());
		
		u.setName("name");
		assertEquals("name", u.getFirstName());
		
		u.setSurname("surname");
		assertEquals("surname", u.getLastName());
		
		u.setAddress("address");
		assertEquals("address", u.getAddress());
		
		u.setDni("dni");
		assertEquals("dni", u.getDni());
		
		u.setEmail("email");
		assertEquals("email", u.getEmail());
		
		u.setNationality("nation");
		assertEquals("nation", u.getNationality());
	}

}
