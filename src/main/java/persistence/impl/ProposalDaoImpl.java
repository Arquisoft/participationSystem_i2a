package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Proposal;
import persistence.JDBCDriver;
import persistence.ProposalDao;
import persistence.conf.Conf;

public class ProposalDaoImpl implements ProposalDao {
	
	private static String SQL_FIND_PROPOSAL_BY_ID = Conf.getInstance().getProperty("SQL_FIND_PROPOSAL_BY_ID");
	private Connection con = JDBCDriver.getConnection();

	@Override
	public Proposal getProposalById(Long id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_PROPOSAL_BY_ID);
			pst.setLong(1, id);

			rs = pst.executeQuery();
			rs.next();

			Integer idProp = rs.getInt("id");
			String content = rs.getString("content");
			Integer votes = rs.getInt("votes");
			String category = rs.getString("category");
			Integer userID = rs.getInt("user_id");
			
			Proposal proposal = new Proposal().setId(idProp).setContent(content).setVotes(votes).setCategory(category).setUserId(userID);
			

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

}
