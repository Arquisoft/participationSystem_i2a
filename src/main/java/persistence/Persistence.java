package persistence;

import persistence.impl.CommentaryDaoImpl;
import persistence.impl.ProposalDaoImpl;
import persistence.impl.UserDaoImpl;

public class Persistence {
	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}

	public static ProposalDao getTaskDao() {
		return new ProposalDaoImpl();
	}

	public static CommentaryDao getCategoryDao() {
		return new CommentaryDaoImpl();
	}
}
