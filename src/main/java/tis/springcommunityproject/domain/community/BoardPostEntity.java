package tis.springcommunityproject.domain.community;


import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.domain.UserEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("BOARD")
public class BoardPostEntity extends PostEntity {

	protected BoardPostEntity() {
	}

	private BoardPostEntity(String title, String content) {
		super(title, content);
	}
	private BoardPostEntity(Long id, String title, String content, UserEntity user) {
		super(id, title, content, user);
	}

	public static BoardPostEntity of(Long id, String title, String content, UserEntity user) {
		return new BoardPostEntity(id, title, content, user);
	}

	public static BoardPostEntity of(String title, String content) {
		return new BoardPostEntity(title, content);
	}

	@Override
	public void updateContent(String content) {
		super.updateContent(content);
	}

	@Override
	public void updateTitle(String title) {
		super.updateTitle(title);
	}
}
