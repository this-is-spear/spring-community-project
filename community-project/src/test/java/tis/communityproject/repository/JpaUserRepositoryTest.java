package tis.communityproject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tis.communityproject.domain.UserEntity;

import java.util.Optional;


@SpringBootTest
class JpaUserRepositoryTest {

  public static final String NAME = "name";
  public static final UserEntity USER = UserEntity.of(NAME, "password");
  private JpaUserRepository userRepository;

  @Test
  void 생성() {
    UserEntity entity = userRepository.save(USER);
    System.out.println("entity = " + entity);
  }

  @Test
  void 이름으로조회() {
    userRepository.save(USER);
    Optional<UserEntity> findUser = userRepository.findByName(NAME);
    System.out.println("findUser.orElse(null) = " + findUser.orElse(null));
  }
}
