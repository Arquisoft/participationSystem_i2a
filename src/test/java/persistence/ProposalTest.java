package persistence;

import static org.junit.Assert.*;

import org.junit.Test;

import dto.Proposal;

public class ProposalTest {
	private ProposalDao pDao = Persistence.getProposalDao();

	@Test
	public void testVoteProposal() {
		Proposal p = pDao.getProposalById(1);
		int previousVotes = p.getVotes();
		pDao.voteProposal(p);
		assertTrue(p.getVotes() == previousVotes + 1);
	}

}
