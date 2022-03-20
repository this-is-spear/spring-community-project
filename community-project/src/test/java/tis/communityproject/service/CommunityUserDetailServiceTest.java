package tis.communityproject.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tis.communityproject.domain.UserEntity;
import tis.communityproject.infra.CommunityUserDetailService;
import tis.communityproject.repository.UserRepository;
import tis.communityproject.service.memorydatabases.MemoryUserRepository;

import static tis.communityproject.service.fixture.UserFixture.사용자;

class CommunityUserDetailServiceTest {
  public static final String USERNAME_NOT_FOUND = "not_found_name";
  private final UserRepository userRepository = new MemoryUserRepository();
  private CommunityUserDetailService communityUserDetailService;

  @BeforeEach
  void setUp() {
    communityUserDetailService = new CommunityUserDetailService(userRepository);
  }

  @Test
  void loadByUserName() {
    UserEntity user = userRepository.save(사용자());
    UserDetails userDetails = communityUserDetailService.loadUserByUsername(user.getName());
    Assertions.assertThat(userDetails.getUsername()).isEqualTo(user.getName());
  }

  @Test
  @DisplayName("회원이 존재하지 않으면 UsernameNotFoundException 예외 발생")
  void NoUser() {
    Assertions.assertThatThrownBy(
        () -> communityUserDetailService.loadUserByUsername(USERNAME_NOT_FOUND)
      ).isInstanceOf(UsernameNotFoundException.class);
  }
}
