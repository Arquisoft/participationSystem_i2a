package persistence;

import java.util.List;

import dto.Commentary;

public interface CommentaryDao {

	List<Commentary> getCommentariesFromProposalId(Integer id);

	void createComment(Commentary p) throws Exception;
	
	Commentary getCommentaryById(Integer parseInt);

	void voteComment(Commentary comment);

	List<Commentary> getCommentariesFromProposalIdOrderedByPopularity(Integer idInt);

	List<Commentary> getCommentariesFromProposalIdOrderedByDate(Integer id);

}
