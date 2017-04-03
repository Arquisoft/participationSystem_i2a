package persistence;

import persistence.impl.CategoryDaoImpl;
import persistence.impl.CommentaryDaoImpl;
import persistence.impl.ProposalDaoImpl;
import persistence.impl.UserDaoImpl;
import persistence.impl.WordDaoImpl;

public class Persistence {
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}

	public static ProposalDao getProposalDao() {
		return new ProposalDaoImpl();
	}

	public static CommentaryDao getCommentaryDao() {
		return new CommentaryDaoImpl();
	}
	
	public static CategoryDao getCategoryDao() {
		return new CategoryDaoImpl();
	}
	
	public static WordDao getWordDao() {
		return new WordDaoImpl();
	}
}
