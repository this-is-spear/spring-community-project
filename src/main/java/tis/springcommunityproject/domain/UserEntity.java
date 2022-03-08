package tis.springcommunityproject.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "Users")
public class UserEntity {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String name;

	//TODO 등록일자와 수정일자는 공통으로 처리 가능하다.
	@Column(updatable = false, nullable = false)
	private LocalDateTime createAt;

	@Column(insertable = false)
	private LocalDateTime updateAt;

	protected UserEntity() {
	}

	private UserEntity(String name) {
		this(null, name, null);
	}

	private UserEntity(Long id, String name, LocalDateTime updateAt) {
		this.id = id;
		this.name = name;
		this.createAt = now();
		this.updateAt = updateAt;
	}

	public static UserEntity of(Long id, String name, LocalDateTime updateAt) {
		return new UserEntity(id, name, updateAt);
	}

	public static UserEntity of( String name) {
		return new UserEntity(name);
	}


	public void updateAt() {
		this.updateAt = now();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void updateName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity user = (UserEntity) o;
		return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
}
