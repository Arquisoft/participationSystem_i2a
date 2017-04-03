package dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryDtoTest {
	Category c;

	@Before
	public void setUp() throws Exception {
		c = new Category();
	}

	@Test
	public void test() {
		assertNotNull(c);
		
		c.setId(new Integer(1));
		assertEquals(new Integer(1), c.getId());
		
		c.setName("blablabla");
		assertEquals("blablabla", c.getName());
		
		assertEquals("Id: 1; Name: blablabla", c.toString());
	}

}
