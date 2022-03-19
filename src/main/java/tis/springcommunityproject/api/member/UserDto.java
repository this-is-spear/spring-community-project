package tis.springcommunityproject.api.member;

import tis.springcommunityproject.domain.InsertionDate;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.area.Area;

public class UserDto {

	private String name;

	private String password;

	private Area area;

	private InsertionDate date;

	private boolean isShopOwner;

	protected UserDto() {
	}

	public UserDto(String name, String password, Area area, InsertionDate date, boolean isShopOwner) {
		this.name = name;
		this.password = password;
		this.area = area;
		this.date = date;
		this.isShopOwner = isShopOwner;
	}

	public UserDto(UserEntity user) {
		this(user.getName(), user.getPassword(), user.getArea(), user.getDate(), user.isShopOwner());
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isShopOwner() {
		return isShopOwner;
	}

	public void setShopOwner(boolean shopOwner) {
		isShopOwner = shopOwner;
	}

	public UserEntity newUserEntity() {
		return UserEntity.of(null, name, password, area, isShopOwner);
	}
}
