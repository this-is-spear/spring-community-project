package tis.communityproject.api.community;


import tis.communityproject.api.member.UserDto;
import tis.communityproject.domain.PostEntity;
import tis.communityproject.domain.community.BoardPostEntity;

import java.time.LocalDateTime;

public class BoardPostDto {
	private String title;

	private String content;

	private UserDto user;

	private LocalDateTime createAt;

	private LocalDateTime updateAt;

	protected BoardPostDto() {
	}

	public BoardPostDto(PostEntity source) {
		this(source.getTitle(), source.getContent(), new UserDto(source.getUser()), source.getDate().getCreateAt(), source.getDate().getUpdateAt());
	}

	public BoardPostDto(String title, String content) {
		this(title, content, null, null, null);
	}

	private BoardPostDto(String title, String content, UserDto user, LocalDateTime createAt, LocalDateTime updateAt) {
		// 유효성 검사

		this.title = title;
		this.content = content;
		this.user = user;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public BoardPostEntity newBoardPostEntity() {
		return BoardPostEntity.of(null,title, content, null);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public UserDto getUser() {
		return user;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

}
