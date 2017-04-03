package hello.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;


public class AddCommentTest {
	
	private AddComment c;

	@Before
	public void setUp() throws Exception {
		c = new AddComment();
	}
	
	@Test
	public void test() throws ParseException {
		assertNotNull(c);
		
		c.setComment("comentario");
		assertEquals("comentario", c.getComment());
		
		c.setComment("");
		assertEquals("", c.getComment());
		
	}

}
