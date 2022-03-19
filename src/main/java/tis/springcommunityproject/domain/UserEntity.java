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

	@Column(unique = true, nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Embedded
	private InsertionDate date;

	@Embedded
	@Column(name = "residence")
	private Area area;

	private boolean isShopOwner;

	protected UserEntity() {
	}

	private UserEntity(String name, String password) {
		this(null, name, password, null, false);
	}

	private UserEntity(Long id, String name, String password, Area area, boolean isShopOwner) {

		// 유효성 검사

		this.id = id;
		this.name = name;
		this.password = password;
		date = InsertionDate.of(now());
		this.area = area;
		this.isShopOwner = isShopOwner;
	}

	public static UserEntity of(Long id, String name, String password, Area area, boolean isShopOwner) {
		return new UserEntity(id, name, password, area, isShopOwner);
	}

	public static UserEntity of(String name, String password) {
		return new UserEntity(name, password);
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

	public String getPassword() {
		return password;
	}

	public boolean isShopOwner() {
		return isShopOwner;
	}

	public void updateName(String name) {
		if (!name.trim().isEmpty() && !this.name.equals(name)) {
			updateAt();
			this.name = name;
		}
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
