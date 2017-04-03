package hello.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import hello.Message;

public class MesaggeTest {
	
	private Message m;

	@Before
	public void setUp() throws Exception {
		m = new Message();
	}
	
	@Test
	public void test() throws ParseException {
		assertNotNull(m);
		
		m.setMessage("comentario");
		assertEquals("comentario", m.getMessage());
		
		m.setMessage("");
		assertEquals("", m.getMessage());
		
	}

}
