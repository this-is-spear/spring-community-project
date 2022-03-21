package tis.communityproject.service;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import tis.communityproject.domain.UserEntity;
//import tis.communityproject.domain.community.BoardPostEntity;
//import tis.communityproject.repository.BoardPostRepository;
//import tis.communityproject.repository.UserRepository;
//import tis.communityproject.service.community.CommunityServiceImpl;
//import tis.communityproject.service.member.MemberService;
//import tis.communityproject.service.member.MemberServiceImpl;
//import tis.communityproject.service.memorydatabases.MemoryBoardPostRepository;
//import tis.communityproject.service.memorydatabases.MemoryUserRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static tis.communityproject.service.fixture.BoardPostFixture.*;
//import static tis.communityproject.service.fixture.UserFixture.사용자;
//
class CommunityServiceImplTest {
//  private static final long BOARD_ID = 1L;
//
//  private final BoardPostRepository postRepository = new MemoryBoardPostRepository();
//  private final UserRepository userRepository = new MemoryUserRepository();
//  private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//  private final MemberService memberService = new MemberServiceImpl(userRepository, passwordEncoder);
//
//  private CommunityServiceImpl communityService;
//
//  @BeforeEach
//  void setUp() {
//    communityService = new CommunityServiceImpl(postRepository, memberService);
//  }
//
//  //포스트 생성
//  @Test
//  @Order(1)
//  @DisplayName("포스트 생성 테스트")
//  void createPostTest() {
//    BoardPostEntity request = 게시글();
//    UserEntity user = userRepository.save(사용자());
//
//    BoardPostEntity result = communityService.create(BOARD_ID, request, user.getId());
//
//    assertThat(result.getTitle()).isEqualTo(request.getTitle());
//    assertThat(result.getContent()).isEqualTo(request.getContent());
//    assertThat(result.getUser()).isEqualTo(user);
//  }
//
//  //포스트 조회
//  @Test
//  @Order(2)
//  @DisplayName("포스트 조회 테스트")
//  void findPostTest() {
//    BoardPostEntity post = postRepository.save(가져오는_게시글());
//
//    assertAll(() -> {
//      assertDoesNotThrow(() -> {
//        BoardPostEntity result = communityService.findOne(BOARD_ID, post.getId(), post.getUser().getId());
//        assertThat(result).isEqualTo(post);
//      });
//    });
//
//  }
//
//  //포스트 삭제
//  @Test
//  @Order(3)
//  @DisplayName("포스트 삭제 테스트")
//  void deletePostTest() {
//    BoardPostEntity post = postRepository.save(가져오는_게시글());
//    assertDoesNotThrow(() -> {
//      communityService.deleteOne(BOARD_ID, post.getId(), post.getUser().getId());
//    });
//  }
//
//  //포스트 업데이트
//  @Test
//  @Order(4)
//  @DisplayName("포스트 업데이트 테스트")
//  void updatePostTest() {
//    BoardPostEntity request = 수정하려는_게시글();
//    BoardPostEntity post = postRepository.save(가져오는_게시글());
//
//    BoardPostEntity result = communityService.updateOne(BOARD_ID, post.getId(), request, post.getUser().getId());
//
//    assertThat(result.getTitle()).isEqualTo(request.getTitle());
//    assertThat(result.getContent()).isEqualTo(request.getContent());
//  }
//
}
