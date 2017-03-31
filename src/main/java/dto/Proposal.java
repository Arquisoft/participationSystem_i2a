package dto;

public class Proposal {
	private Integer id;
	private String content;
	private Integer votes;
	private Integer categoryId;
	private Integer userId;

	public Proposal() {
	}

	public Proposal(String content, Integer votes, Integer categoryId, Integer usedId) {
		super();
		this.content = content;
		this.votes = votes;
		this.categoryId = categoryId;
		this.userId = usedId;
	}

	public Integer getId() {
		return id;
	}

	public Proposal setId(Integer id) {
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

	public Integer getVotes() {
		return votes;
	}

	public Proposal setVotes(Integer votes) {
		this.votes = votes;
		return this;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public Proposal setCategory(Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public Proposal setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public String toString() {
		return "Content: " + content + "; Votes: " + votes + "; "
				+ "Category: " + categoryId + "; User: " + userId;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + votes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}

}
