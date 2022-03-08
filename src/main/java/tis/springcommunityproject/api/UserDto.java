package tis.springcommunityproject.api;

import tis.springcommunityproject.domain.UserEntity;

public class UserDto {

	private final String name;

	public UserDto(String name) {
		this.name = name;
	}

	public UserDto(UserEntity user) {
		this(user.getName());
	}

	public String getName() {
		return name;
	}

	public UserEntity newUserEntity() {
		return UserEntity.of(name);
	}
}
