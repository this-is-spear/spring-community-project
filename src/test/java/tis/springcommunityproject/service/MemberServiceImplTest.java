package tis.springcommunityproject.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.repository.JpaUserRepository;
import tis.springcommunityproject.service.member.MemberServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tis.springcommunityproject.service.fixture.UserFixture.사용자;
import static tis.springcommunityproject.service.fixture.UserFixture.회원_가입;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

  @Mock
  private JpaUserRepository userRepository;

  @InjectMocks
  private MemberServiceImpl memberService;

  @Test
  @DisplayName("회원 가입 테스트")
  void join() {
    UserEntity request = 회원_가입();
    when(userRepository.save(request)).thenReturn(request);

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
    UserEntity user = 사용자();
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

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
    UserEntity user = 사용자();
    doNothing().when(userRepository).deleteById(user.getId());

    assertAll(() ->
      assertDoesNotThrow(() -> {
        memberService.deleteOne(user.getId());
      })
    );

  }
}
