package tis.springcommunityproject.api.community;

import tis.springcommunityproject.domain.PostEntity;

import java.util.List;

public class BoardDto {
	private List<PostEntity> postList;

	private String title;

	protected BoardDto() {
	}

	public BoardDto(String title) {
		this(null, title);
	}

	public BoardDto(List<PostEntity> postList, String title) {
		// 유효성 검사

		this.postList = postList;
		this.title = title;
	}

	public List<PostEntity> getPostList() {
		return postList;
	}

	public String getTitle() {
		return title;
	}
}
