package dto;

public class Proposal {
	private Long id;

	private String content;
	private int votes;
	private String category;
	private Long userId;

	public Proposal() {
	}

	public Proposal(String content, int votes, String category, Long usedId) {
		super();
		this.content = content;
		this.votes = votes;
		this.category = category;
		this.userId = usedId;
	}

	public Long getId() {
		return id;
	}

	public Proposal setId(Long id) {
		this.id = id;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Proposal setContent(String content) {
		this.content = content;
		return this;
	}

	public int getVotes() {
		return votes;
	}

	public Proposal setVotes(int votes) {
		this.votes = votes;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public Proposal setCategory(String category) {
		this.category = category;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public Proposal setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public String toString() {
		return "Content: " + content + "; Votes: " + votes + "; "
				+ "Category: " + category + "; User: " + userId;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Proposal other = (Proposal) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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

}
