package tis.springcommunityproject.service;

import org.junit.jupiter.api.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.community.BoardPostEntity;
import tis.springcommunityproject.repository.BoardPostRepository;
import tis.springcommunityproject.repository.UserRepository;
import tis.springcommunityproject.service.community.CommunityServiceImpl;
import tis.springcommunityproject.service.member.MemberService;
import tis.springcommunityproject.service.member.MemberServiceImpl;
import tis.springcommunityproject.service.memorydatabases.MemoryBoardPostRepository;
import tis.springcommunityproject.service.memorydatabases.MemoryUserRepository;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static tis.springcommunityproject.service.fixture.BoardPostFixture.*;
import static tis.springcommunityproject.service.fixture.UserFixture.사용자;

class CommunityServiceImplTest {
  private static final long BOARD_ID = 1L;

  private final BoardPostRepository postRepository = new MemoryBoardPostRepository();
  private final UserRepository userRepository = new MemoryUserRepository();
  private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  private final MemberService memberService = new MemberServiceImpl(userRepository, passwordEncoder);

  private CommunityServiceImpl communityService;

  @BeforeEach
  void setUp() {
    communityService = new CommunityServiceImpl(postRepository, memberService);
  }

  //포스트 생성
  @Test
  @Order(1)
  @DisplayName("포스트 생성 테스트")
  void createPostTest() {
    BoardPostEntity request = 게시글();
    UserEntity user = userRepository.save(사용자());

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
    BoardPostEntity post = postRepository.save(가져오는_게시글());

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
    BoardPostEntity post = postRepository.save(가져오는_게시글());
    assertDoesNotThrow(() -> {
      communityService.deleteOne(BOARD_ID, post.getId(), post.getUser().getId());
    });
  }

  //포스트 업데이트
  @Test
  @Order(4)
  @DisplayName("포스트 업데이트 테스트")
  void updatePostTest() {
    BoardPostEntity request = 수정하려는_게시글();
    BoardPostEntity post = postRepository.save(가져오는_게시글());

    BoardPostEntity result = communityService.updateOne(BOARD_ID, post.getId(), request, post.getUser().getId());

    assertThat(result.getTitle()).isEqualTo(request.getTitle());
    assertThat(result.getContent()).isEqualTo(request.getContent());
  }

}
