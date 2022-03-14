package tis.springcommunityproject.service.fixture;

import tis.springcommunityproject.domain.UserEntity;

public class UserFixture {

  public static final long USER_ID = 1L;

  public static UserEntity 사용자() {
    return UserEntity.of(USER_ID, "user", null);
  }
}
