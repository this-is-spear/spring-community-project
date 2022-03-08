package tis.springcommunityproject.domain;

import tis.springcommunityproject.domain.area.Area;

import javax.persistence.*;
import java.util.Objects;

import static java.time.LocalDateTime.now;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String name;

	@Embedded
	private InsertionDate date;

	@Embedded
	private Area area;

	protected UserEntity() {
	}

	private UserEntity(String name) {
		this(null, name, null);
	}

	private UserEntity(Long id, String name, Area area) {

		// 유효성 검사

		this.id = id;
		this.name = name;
		date = InsertionDate.of(now());
		this.area = area;
	}

	public static UserEntity of(Long id, String name, Area area) {
		return new UserEntity(id, name, area);
	}

	public static UserEntity of(String name) {
		return new UserEntity(name);
	}

	public void updateAt() {
		this.date.changeUpdateAt();
	}

	public Long getId() {
		return id;
	}

	public Area getArea() {
		return area;
	}

	public String getName() {
		return name;
	}

	public InsertionDate getDate() {
		return date;
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
