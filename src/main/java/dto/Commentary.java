package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import persistence.Persistence;

public class Commentary {
	private Integer id;

	private String content;
	private int votes;
	private Date fecha;
	private Integer proposalId;
	private Integer userId;

	public Commentary() {
	}

	public Commentary(Integer idComment, String content, Integer votes, Date fecha, Integer userID,
			Integer proposalID) {
		super();
		this.id = idComment;
		this.content = content;
		this.votes = votes;
		this.fecha = fecha;
		this.proposalId = proposalID;
		this.userId = userID;
	}
	
	public Commentary(String content, int votes, Date fecha, Integer proposalId, Integer usedId) {
		super();
		this.content = content;
		this.votes = votes;
		this.fecha = fecha;
		this.proposalId = proposalId;
		this.userId = usedId;
	}

	public Commentary(String content, Integer propId, Integer userId) {
		this(content, 0, new Date(), propId, userId);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getProposalId() {
		return proposalId;
	}

	public void setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
	}

	public Integer getUserId() {
		return userId;
	}
	
	public String getUserName(){
		User user = Persistence.getUserDao().getUserById(userId);
		return user.getFirstName() + " " + user.getLastName();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((proposalId == null) ? 0 : proposalId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + votes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}

	@Override
	public String toString() {
		String simpleDate = new SimpleDateFormat("dd/MM/yyyy").format(fecha);
		return "Content: " + content + "; Votes: " + votes + "; " + "Date: " + simpleDate + "; Proposal: " + proposalId
				+ "; User: " + userId;

	}
}
