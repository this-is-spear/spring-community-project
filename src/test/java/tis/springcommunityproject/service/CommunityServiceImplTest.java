package tis.springcommunityproject.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.community.BoardPostEntity;
import tis.springcommunityproject.repository.JpaBoardPostRepository;
import tis.springcommunityproject.service.community.CommunityServiceImpl;
import tis.springcommunityproject.service.member.MemberService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static tis.springcommunityproject.service.fixture.BoardPostFixture.*;
import static tis.springcommunityproject.service.fixture.UserFixture.사용자;

@ExtendWith(MockitoExtension.class)
class CommunityServiceImplTest {
  private static final long BOARD_ID = 1L;

  @Mock
  private JpaBoardPostRepository postRepository;

  @Mock
  private MemberService memberService;

  @InjectMocks
  private CommunityServiceImpl communityService;

  //포스트 생성
  @Test
  @Order(1)
  @DisplayName("포스트 생성 테스트")
  void createPostTest() {
    BoardPostEntity request = 게시글();
    UserEntity user = 사용자();

    when(memberService.findOne(any())).thenReturn(user);
    when(postRepository.save(any())).thenReturn(request);

    BoardPostEntity result = communityService.create(BOARD_ID, request, user.getId());

    assertThat(result.getTitle()).isEqualTo(request.getTitle());
    assertThat(result.getContent()).isEqualTo(request.getContent());
    assertThat(result.getUser()).isEqualTo(user);
  }

  //포스트 조회
  @Test
  @Order(2)
  @DisplayName("포스트 조회 테스트")
  void findPostTest() {
    BoardPostEntity post = 가져오는_게시글();

    when(postRepository.findById(any())).thenReturn(Optional.of(post));

    assertAll(() -> {
      assertDoesNotThrow(() -> {
        BoardPostEntity result = communityService.findOne(BOARD_ID, post.getId(), post.getUser().getId());
        assertThat(result).isEqualTo(post);
      });
    });

  }

  //포스트 삭제
  @Test
  @Order(3)
  @DisplayName("포스트 삭제 테스트")
  void deletePostTest() {
    BoardPostEntity post = 삭제하려는_게시글();

    when(postRepository.findById(any())).thenReturn(Optional.of(post));
    doNothing().when(postRepository).deleteById(any());
    assertDoesNotThrow(() -> {
      communityService.deleteOne(BOARD_ID, post.getId(), post.getUser().getId());
      verify(postRepository).deleteById(any(Long.TYPE));
    });
  }

  //포스트 업데이트
  @Test
  @Order(4)
  @DisplayName("포스트 업데이트 테스트")
  void updatePostTest() {
    BoardPostEntity request = 수정하려는_게시글();
    BoardPostEntity post = 가져오는_게시글();
    when(postRepository.findById(any())).thenReturn(Optional.of(post));

    when(postRepository.save(any())).thenReturn(post);
    BoardPostEntity result = communityService.updateOne(BOARD_ID, post.getId(), request, post.getUser().getId());

    assertThat(result.getTitle()).isEqualTo(request.getTitle());
    assertThat(result.getContent()).isEqualTo(request.getContent());
  }

}
