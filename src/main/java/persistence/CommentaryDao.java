package persistence;

import java.util.List;

import dto.Commentary;

public interface CommentaryDao {

	List<Commentary> getCommentariesFromProposalId(Integer id);

	void createComment(Commentary p);

	List<Commentary> getCommentariesByPopularity();

	List<Commentary> getCommentariesByDate();

}
