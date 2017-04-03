package persistence;

import java.util.List;

import dto.Commentary;

public interface CommentaryDao {

	List<Commentary> getCommentariesFromProposalId(Integer id);

	void createComment(Commentary p) throws Exception;

	List<Commentary> getCommentariesByPopularity();

	List<Commentary> getCommentariesByDate();

	Commentary getCommentaryById(Integer parseInt);

	void voteComment(Commentary comment);

}
