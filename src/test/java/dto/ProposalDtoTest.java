package dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ProposalDtoTest {
	Proposal p;

	@Before
	public void setUp() throws Exception {
		p = new Proposal();
	}

	@Test
	public void test() {
		assertNotNull(p);
		
		p.setId(1);
		assertEquals(new Integer(1), p.getId());
		
		p.setCategoryId(new Integer(1));
		assertEquals(new Integer(1), p.getCategoryId());
		
		p.setContent("Blablabla");
		assertEquals("Blablabla", p.getContent());
		
		p.setUserId(1);
		assertEquals(new Integer(1), p.getUserId());
		
		p.setVotes(1);
		assertEquals(new Integer(1), p.getVotes());
		
		assertEquals("Id: 1; Content: Blablabla; Votes: 1; Category: 1; User: 1", p.toString());
		
	}

}
