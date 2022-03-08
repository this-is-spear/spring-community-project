package tis.springcommunityproject.domain;


import javax.persistence.*;
import java.util.Objects;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "posts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type")
@DiscriminatorValue("POST")
public abstract class PostEntity {
	@Id
	@GeneratedValue
	@Column(name = "post_id")
	private Long id;

	private String title;

	private String content;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Embedded
	private InsertionDate date;

	protected PostEntity() {
	}

	protected PostEntity(Long id, String title, String content, UserEntity user) {

		// 유효성 검사

		this.id = id;
		this.title = title;
		this.content = content;
		this.user = user;
		date = InsertionDate.of(now());
	}

	protected PostEntity(String title, String content) {
		this(null, title, content, null);
	}

	public void updateContent(String content) {
		this.content = content;
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	public void updateAt() {
		this.date.changeUpdateAt();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public UserEntity getUser() {
		return user;
	}

	public InsertionDate getDate() {
		return date;
	}

	public void updateUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PostEntity post = (PostEntity) o;
		return Objects.equals(getId(), post.getId()) && Objects.equals(getTitle(), post.getTitle()) && Objects.equals(getContent(), post.getContent()) && Objects.equals(getUser(), post.getUser()) && Objects.equals(getDate(), post.getDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getTitle(), getContent(), getUser(), getDate());
	}
}
