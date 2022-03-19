package tis.springcommunityproject.service.fixture;

import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.area.*;

import java.math.BigDecimal;

public class UserFixture {

  public static final long USER_ID = 1L;

  public static UserEntity 회원_가입() {
    return UserEntity.of(null, "user", "password", 사는_지역(), false);
  }

  public static UserEntity 사용자() {
    return UserEntity.of(USER_ID, "user", "password", 사는_지역(), false);
  }

  public static Area 사는_지역() {
    return new Area(new State("서울시"), new City("서초구"), new Region("서초동"), new Gps(BigDecimal.valueOf(37.4877), BigDecimal.valueOf(127.0174)));
  }
}
