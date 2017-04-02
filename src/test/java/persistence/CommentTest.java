package persistence;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dto.Commentary;

public class CommentTest {
	private CommentaryDao cDao = Persistence.getCommentaryDao();

	@Test
	public void testVoteComment() {
		Commentary c = cDao.getCommentaryById(1);
		int previousVotes = c.getVotes();
		cDao.voteComment(c);
		assertTrue(c.getVotes() == previousVotes + 1);
	}

}
