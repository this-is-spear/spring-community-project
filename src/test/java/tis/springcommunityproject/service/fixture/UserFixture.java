package tis.springcommunityproject.service.fixture;

import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.area.*;

public class UserFixture {

  public static final long USER_ID = 1L;

  public static UserEntity 회원_가입() {
    return UserEntity.of(null, "user", 사는_지역());
  }

  public static UserEntity 사용자() {
    return UserEntity.of(USER_ID, "user", 사는_지역());
  }

  public static Area 사는_지역() {
    return new Area(new State("Republic of korea"), new Town("Busan-si"), new SiGunGu("nam-gu"), new Gps("latitude", "longitude"));
  }
}
