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

public class ProposalDaoImpl implements ProposalDao {

//	private static String SQL_FIND_PROPOSAL_BY_ID = Conf.getInstance().getProperty("SQL_FIND_PROPOSAL_BY_ID");
	//private static String SQL_ALL_PROPOSALS = Conf.getInstance().getProperty("SQL_ALL_PROPOSALS");
	//private static String SQL_DELETE_PROPOSAL = Conf.getInstance().getProperty("SQL_DELETE_PROPOSAL");
	//private static String SQL_INSERT_PROPOSAL = Conf.getInstance().getProperty("SQL_INSERT_PROPOSAL");

	private Connection con = JDBCDriver.getConnection();

	@Override
	public Proposal getProposalById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("SELECT * FROM PUBLIC.PROPOSAL WHERE ID=?");
			pst.setInt(1, id);

			rs = pst.executeQuery();
			rs.next();

			Integer idProp = rs.getInt("id");
			String content = rs.getString("content");
			Integer votes = rs.getInt("votes");
			Integer category_id = rs.getInt("category_id");
			Integer userID = rs.getInt("user_id");

			Proposal proposal = new Proposal();
			proposal.setVotes(votes);
			proposal.setUserId(userID);
			proposal.setContent(content);
			proposal.setCategory(category_id);
			proposal.setId(idProp);

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
			pst = con.prepareStatement("SELECT * FROM PUBLIC.PROPOSAL");

			rs = pst.executeQuery();
			while (rs.next()) {

				Integer idProp = rs.getInt("id");
				String content = rs.getString("content");
				Integer votes = rs.getInt("votes");
				Integer category_id = rs.getInt("category_id");
				Integer userID = rs.getInt("user_id");

				Proposal proposal = new Proposal(content, votes, category_id, userID);
				proposal.setId(idProp);

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

	/*
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
	}*/

	@Override
	public void createProposal(Proposal p) {
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("INSERT INTO PUBLIC.PROPOSAL(content, votes, user_id) VALUES (?,?,?)");
			pst.setString(1, p.getContent());
			pst.setInt(2, p.getVotes());
			pst.setInt(3, p.getUserId());
			//pst.setInt(4, p.getCategoryId());

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
	public void voteProposal(Proposal p) {
		p.setVotes(p.getVotes() + 1);
		updateProposal(p);
		
	}
	
	public void updateProposal(Proposal p){
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("UPDATE PUBLIC.PROPOSAL SET CONTENT = ?, VOTES = ?, CATEGORY_ID = ?"
					+ "WHERE ID = ?");
			pst.setString(1, p.getContent());
			pst.setInt(2, p.getVotes());
			pst.setInt(3, p.getCategoryId());
			pst.setInt(4,  p.getId());

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
	public void deleteProposalById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
