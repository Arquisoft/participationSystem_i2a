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
import persistence.JDBCDriver;
import persistence.conf.Conf;

public class CommentaryDaoImpl implements CommentaryDao {
	
	private static String SQL_COMMENT_ORDER_BY_DATE = Conf.getInstance().getProperty("SQL_COMMENT_ORDER_BY_DATE");
	private static String SQL_INSERT_COMMENT = Conf.getInstance().getProperty("SQL_INSERT_COMMENT");
	private static String SQL_COMMENT_ORDER_BY_POPULARITY = Conf.getInstance().getProperty("SQL_COMMENT_ORDER_BY_POPULARITY");
	private static String SQL_PROPOSAL_COMMENT  = Conf.getInstance().getProperty("SQL_PROPOSAL_COMMENT");
	private Connection con = JDBCDriver.getConnection();
	
	@Override
	public List<Commentary> getCommentariesFromProposalId(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Commentary> comments = new ArrayList<Commentary>();
		try {
			pst = con.prepareStatement(SQL_PROPOSAL_COMMENT);

			rs = pst.executeQuery();
			while(rs.next()){

			Integer idComment = rs.getInt("id");
			String content = rs.getString("content");
			Integer votes = rs.getInt("votes");
			Date fecha = rs.getDate("fecha");
			Integer userID = rs.getInt("user_id");
			Integer proposalID = rs.getInt("proposal_id");

			Commentary comment = new Commentary().setId(idComment).setContent(content).setVotes(votes)
					.setFecha(fecha).setUserId(userID).setProposalId(proposalID);

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
	public void createComment(Commentary p) {
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_INSERT_COMMENT);
			pst.setInt(1, p.getId());
			pst.setString(2, p.getContent());
			pst.setInt(3, p.getVotes());
			pst.setDate(4, (java.sql.Date) p.getFecha());
			pst.setInt(5, p.getUserId());
			pst.setInt(6, p.getProposalId());
			
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
	
	@Override
	public List<Commentary> getCommentariesByDate() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Commentary> comments = new ArrayList<Commentary>();
		try {
			pst = con.prepareStatement(SQL_COMMENT_ORDER_BY_DATE );

			rs = pst.executeQuery();
			while(rs.next()){

			Integer idComment = rs.getInt("id");
			String content = rs.getString("content");
			Integer votes = rs.getInt("votes");
			Date fecha = rs.getDate("fecha");
			Integer userID = rs.getInt("user_id");
			Integer proposalID = rs.getInt("proposal_id");

			Commentary comment = new Commentary().setId(idComment).setContent(content).setVotes(votes)
					.setFecha(fecha).setUserId(userID).setProposalId(proposalID);

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
			while(rs.next()){

			Integer idComment = rs.getInt("id");
			String content = rs.getString("content");
			Integer votes = rs.getInt("votes");
			Date fecha = rs.getDate("fecha");
			Integer userID = rs.getInt("user_id");
			Integer proposalID = rs.getInt("proposal_id");

			Commentary comment = new Commentary().setId(idComment).setContent(content).setVotes(votes)
					.setFecha(fecha).setUserId(userID).setProposalId(proposalID);

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

}
