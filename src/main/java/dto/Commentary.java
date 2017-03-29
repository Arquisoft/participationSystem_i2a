package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Commentary {
	private Long id;

	private String content;
	private int votes;
	private Date fecha;
	private Long proposalId;
	private Long userId;

	public Commentary() {
	}

	public Commentary(String content, int votes, Date fecha, Long proposalId, Long usedId) {
		super();
		this.content = content;
		this.votes = votes;
		this.fecha = fecha;
		this.proposalId = proposalId;
		this.userId = usedId;
	}

	public Long getId() {
		return id;
	}

	public Commentary setId(Long id) {
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

	public Long getProposalId() {
		return proposalId;
	}

	public Commentary setProposalId(Long proposalId) {
		this.proposalId = proposalId;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public Commentary setUserId(Long userId) {
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commentary other = (Commentary) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (proposalId == null) {
			if (other.proposalId != null)
				return false;
		} else if (!proposalId.equals(other.proposalId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (votes != other.votes)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String simpleDate = new SimpleDateFormat("dd/MM/yyyy").format(fecha);
		return "Content: " + content + "; Votes: " + votes + "; " + "Date: " + simpleDate + "; Proposal: " + proposalId
				+ "; User: " + userId;

	}
}
