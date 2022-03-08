package tis.springcommunityproject.api.member;

import tis.springcommunityproject.domain.InsertionDate;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.area.Area;

public class UserDto {

	private String name;

	private Area area;

	private InsertionDate date;

	protected UserDto() {
	}

	public UserDto(String name, Area area, InsertionDate date) {
		this.name = name;
		this.area = area;
		this.date = date;
	}

	public UserDto(UserEntity user) {
		this(user.getName(), user.getArea(), user.getDate());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public InsertionDate getDate() {
		return date;
	}

	public void setDate(InsertionDate date) {
		this.date = date;
	}

	public UserEntity newUserEntity() {
		return UserEntity.of(null, name, area);
	}
}
