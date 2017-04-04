package persistence;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import dto.Commentary;

public class CommentaryPersistenceTest {
	private CommentaryDao cDao = Persistence.getCommentaryDao();

	@Test
	public void testVoteComment() {
		Commentary c = cDao.getCommentaryById(1);
		int previousVotes = c.getVotes();
		cDao.voteComment(c);
		assertTrue(c.getVotes() == previousVotes + 1);
	}
	
	@Test
	public void testCreateComment() {
		Commentary c = new Commentary("TEST", 0, new Date(), 1, 1);
		try {
			cDao.createComment(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Commentary> comments = cDao.getCommentariesFromProposalId(1);
		c.setId(comments.get(comments.size()-1).getId());
		assertEquals(c, comments.get(comments.size()-1));
		
		List<Commentary> pop = cDao.getCommentariesFromProposalIdOrderedByPopularity(1);
		List<Commentary> dat = cDao.getCommentariesFromProposalIdOrderedByDate(1);
		
		assertTrue(pop.contains(c));
		assertTrue(dat.contains(c));
	}

}
