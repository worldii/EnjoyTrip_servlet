package board.dto;

public class Board {
	private int articleNo;
	private String userId;
	private String title;
	private String content;
	
	public Board(String userId, String title, String content) {
		super();
		this.userId = userId;
		this.title = title;
		this.content = content;
	}

	public Board(int articleNo, String userId, String title, String content) {
		super();
		this.articleNo = articleNo;
		this.userId = userId;
		this.title = title;
		this.content = content;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Board [articleNo=" + articleNo + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ "]";
	}
	
	
}
