package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.Commentary;
import persistence.CommentaryDao;
import persistence.Database;
import persistence.Persistence;

public class CommentaryDaoImpl implements CommentaryDao {

	// private static String SQL_COMMENT_ORDER_BY_DATE =
	// Conf.getInstance().getProperty("SQL_COMMENT_ORDER_BY_DATE");
	// private static String SQL_INSERT_COMMENT =
	// Conf.getInstance().getProperty("SQL_INSERT_COMMENT");
	// private static String SQL_COMMENT_ORDER_BY_POPULARITY =
	// Conf.getInstance().getProperty("SQL_COMMENT_ORDER_BY_POPULARITY");
	// private static String SQL_PROPOSAL_COMMENT =
	// Conf.getInstance().getProperty("SQL_PROPOSAL_COMMENT");

	private static String SQL_COMMENT_ORDER_BY_POPULARITY = "SELECT * FROM PUBLIC.COMMENTARY ORDER BY VOTES";
	private static String SQL_INSERT_COMMENT = "INSERT INTO PUBLIC.COMMENTARY (content, votes, fecha, user_id, proposal_id) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static String SQL_PROPOSAL_COMMENT = "SELECT * FROM PUBLIC.COMMENTARY WHERE PROPOSAL_ID=?";
	private static String SQL_COMMENT_ORDER_BY_DATE = "SELECT * FROM PUBLIC.COMMENTARY ORDER BY FECHA";
	private static String SQL_FIND_COMMENT_BY_ID = "SELECT * FROM PUBLIC.COMMENTARY WHERE ID=?";
	private Connection con = Database.getConnection();

	@Override
	public List<Commentary> getCommentariesFromProposalId(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Commentary> comments = new ArrayList<Commentary>();
		try {
			pst = con.prepareStatement(SQL_PROPOSAL_COMMENT);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			while (rs.next()) {

				Integer idComment = rs.getInt("id");
				String content = rs.getString("content");
				Integer votes = rs.getInt("votes");
				Date fecha = rs.getDate("fecha");
				Integer userID = rs.getInt("user_id");
				Integer proposalID = rs.getInt("proposal_id");

				Commentary comment = new Commentary(idComment, content, votes, fecha, userID, proposalID);

				comments.add(comment);
			}
			return comments;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public void createComment(Commentary p) throws Exception {
		PreparedStatement pst = null;
		try {
			if (Persistence.getWordDao().checkContent(p.getContent())) {
				pst = con.prepareStatement(SQL_INSERT_COMMENT);
				pst.setString(1, p.getContent());
				pst.setInt(2, p.getVotes());
				pst.setDate(3, new java.sql.Date(p.getFecha().getTime()));
				pst.setInt(4, p.getUserId());
				pst.setInt(5, p.getProposalId());

				pst.executeUpdate();
			} else {
				throw new Exception("Hay palabras no permitidas");
			}

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public List<Commentary> getCommentariesByDate() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Commentary> comments = new ArrayList<Commentary>();
		try {
			pst = con.prepareStatement(SQL_COMMENT_ORDER_BY_DATE);

			rs = pst.executeQuery();
			while (rs.next()) {

				Integer idComment = rs.getInt("id");
				String content = rs.getString("content");
				Integer votes = rs.getInt("votes");
				Date fecha = rs.getDate("fecha");
				Integer userID = rs.getInt("user_id");
				Integer proposalID = rs.getInt("proposal_id");

				Commentary comment = new Commentary(idComment, content, votes, fecha, userID, proposalID);

				comments.add(comment);
			}
			return comments;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public List<Commentary> getCommentariesByPopularity() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Commentary> comments = new ArrayList<Commentary>();
		try {
			pst = con.prepareStatement(SQL_COMMENT_ORDER_BY_POPULARITY);

			rs = pst.executeQuery();
			while (rs.next()) {

				Integer idComment = rs.getInt("id");
				String content = rs.getString("content");
				Integer votes = rs.getInt("votes");
				Date fecha = rs.getDate("fecha");
				Integer userID = rs.getInt("user_id");
				Integer proposalID = rs.getInt("proposal_id");

				Commentary comment = new Commentary(idComment, content, votes, fecha, userID, proposalID);

				comments.add(comment);
			}
			return comments;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public Commentary getCommentaryById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(SQL_FIND_COMMENT_BY_ID);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			rs.next();
			Integer idComment = rs.getInt("id");
			String content = rs.getString("content");
			Integer votes = rs.getInt("votes");
			Date fecha = rs.getDate("fecha");
			Integer userID = rs.getInt("user_id");
			Integer proposalID = rs.getInt("proposal_id");

			Commentary comment = new Commentary(idComment, content, votes, fecha, userID, proposalID);

			return comment;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public void voteComment(Commentary c) {
		c.setVotes(c.getVotes() + 1);
		updateComment(c);
	}

	public void updateComment(Commentary c) {
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(
					"UPDATE PUBLIC.COMMENTARY SET CONTENT = ?, " + "VOTES = ?, FECHA = ? WHERE ID = ?");
			pst.setString(1, c.getContent());
			pst.setInt(2, c.getVotes());
			pst.setDate(3, new java.sql.Date(c.getFecha().getTime()));
			pst.setInt(4, c.getId());

			pst.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

}
