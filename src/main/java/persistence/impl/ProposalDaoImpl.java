package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Proposal;
import persistence.JDBCDriver;
import persistence.ProposalDao;
import persistence.conf.Conf;

public class ProposalDaoImpl implements ProposalDao {

	private static String SQL_FIND_PROPOSAL_BY_ID = Conf.getInstance().getProperty("SQL_FIND_PROPOSAL_BY_ID");
	private static String SQL_ALL_PROPOSALS = Conf.getInstance().getProperty("SQL_ALL_PROPOSALS");
	private static String SQL_DELETE_PROPOSAL = Conf.getInstance().getProperty("SQL_DELETE_PROPOSAL");
	private static String SQL_INSERT_PROPOSAL = Conf.getInstance().getProperty("SQL_INSERT_PROPOSAL");
	
	private Connection con = JDBCDriver.getConnection();

	@Override
	public Proposal getProposalById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_PROPOSAL_BY_ID);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			rs.next();

			Integer idProp = rs.getInt("id");
			String content = rs.getString("content");
			Integer votes = rs.getInt("votes");
			Integer category_id = rs.getInt("category_id");
			Integer userID = rs.getInt("user_id");

			Proposal proposal = new Proposal().setId(idProp).setContent(content).setVotes(votes).setCategory(category_id)
					.setUserId(userID);

			return proposal;

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
	public List<Proposal> getProposals() {
		List<Proposal> proposals = new ArrayList<Proposal>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_ALL_PROPOSALS);

			rs = pst.executeQuery();
			while(rs.next()){

			Integer idProp = rs.getInt("id");
			String content = rs.getString("content");
			Integer votes = rs.getInt("votes");
			Integer category_id = rs.getInt("category_id");
			Integer userID = rs.getInt("user_id");

			Proposal proposal = new Proposal().setId(idProp).setContent(content).setVotes(votes).setCategory(category_id)
					.setUserId(userID);

			proposals.add(proposal);
			}
			
			return proposals;

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
	public void deleteProposalById(Integer id) {
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_DELETE_PROPOSAL);
			pst.setInt(1, id);
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
	public void createProposal(Proposal p) {
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_INSERT_PROPOSAL);
			pst.setInt(1, p.getId());
			pst.setString(2, p.getContent());
			pst.setInt(3, p.getVotes());
			pst.setInt(4, p.getUserId());
			pst.setInt(4, p.getCategoryId());
			
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
