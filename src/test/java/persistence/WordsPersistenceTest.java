package persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class WordsPersistenceTest {
	
	private WordDao dao = Persistence.getWordDao();
	

	@Test
	public void test() {
		List<String> words = new ArrayList<>();
		words.add("Shit");
		dao.add(words);
		
		assertEquals("Shit", dao.findAll().get(0));
		
		assertTrue(dao.checkContent("Just a text with shit"));
		
	}

}
