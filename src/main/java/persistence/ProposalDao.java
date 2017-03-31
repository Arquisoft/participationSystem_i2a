package persistence;

import java.util.List;

import dto.Proposal;

public interface ProposalDao {

	Proposal getProposalById(Integer id);

	List<Proposal> getProposals();

	void deleteProposalById(Integer id);

	void createProposal(Proposal p);

}
