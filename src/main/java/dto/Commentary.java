package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Commentary {
	private Integer id;

	private String content;
	private int votes;
	private Date fecha;
	private Integer proposalId;
	private Integer userId;

	public Commentary() {
	}

	public Commentary(String content, int votes, Date fecha, Integer proposalId, Integer usedId) {
		super();
		this.content = content;
		this.votes = votes;
		this.fecha = fecha;
		this.proposalId = proposalId;
		this.userId = usedId;
	}

	public Integer getId() {
		return id;
	}

	public Commentary setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Commentary setContent(String content) {
		this.content = content;
		return this;
	}

	public int getVotes() {
		return votes;
	}

	public Commentary setVotes(int votes) {
		this.votes = votes;
		return this;
	}

	public Date getFecha() {
		return fecha;
	}

	public Commentary setFecha(Date fecha) {
		this.fecha = fecha;
		return this;
	}

	public Integer getProposalId() {
		return proposalId;
	}

	public Commentary setProposalId(Integer proposalId) {
		this.proposalId = proposalId;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public Commentary setUserId(Integer userId) {
		this.userId = userId;
		return this;
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
