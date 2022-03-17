package tis.springcommunityproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.repository.UserRepository;
import tis.springcommunityproject.service.member.MemberServiceImpl;
import tis.springcommunityproject.service.memorydatabases.MemoryUserRepository;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static tis.springcommunityproject.service.fixture.UserFixture.사용자;
import static tis.springcommunityproject.service.fixture.UserFixture.회원_가입;

class MemberServiceImplTest {

  private final UserRepository userRepository = new MemoryUserRepository();

  private MemberServiceImpl memberService;

  @BeforeEach
  void setUp() {
    memberService = new MemberServiceImpl(userRepository);
  }

  @Test
  @DisplayName("회원 가입 테스트")
  void join() {
    UserEntity request = 회원_가입();

    assertAll(() ->
      assertDoesNotThrow(() -> {
        UserEntity result = memberService.join(request);
        assertThat(request.getName()).isEqualTo(result.getName());
      })
    );
  }

  @Test
  @DisplayName("회원 정보 조회 테스트")
  void findOne() {
    UserEntity user = userRepository.save(사용자());

    assertAll(() ->
      assertDoesNotThrow(() -> {
        UserEntity result = memberService.findOne(user.getId());
        assertThat(result.getName()).isEqualTo(user.getName());
        assertThat(result.getId()).isEqualTo(user.getId());
      })
    );
  }

  @Test
  @DisplayName("회원 정보 삭제 테스트")
  void deleteOne() {
    UserEntity user = userRepository.save(사용자());

    assertAll(() ->
      assertDoesNotThrow(() -> {
        memberService.deleteOne(user.getId());
      })
    );

  }
}
