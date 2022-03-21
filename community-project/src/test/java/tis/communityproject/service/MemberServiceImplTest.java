package tis.communityproject.service;
//
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import tis.communityproject.domain.UserEntity;
//import tis.communityproject.repository.UserRepository;
//import tis.communityproject.service.member.MemberServiceImpl;
//import tis.communityproject.service.memorydatabases.MemoryUserRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static tis.communityproject.service.fixture.UserFixture.사용자;
//import static tis.communityproject.service.fixture.UserFixture.회원_가입;
//
class MemberServiceImplTest {
//
//  private final UserRepository userRepository = new MemoryUserRepository();
//  private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//  private MemberServiceImpl memberService;
//
//  @BeforeEach
//  void setUp() {
//    memberService = new MemberServiceImpl(userRepository, passwordEncoder);
//  }
//
//  @Test
//  @DisplayName("회원 가입 테스트")
//  void join() {
//    UserEntity request = 회원_가입();
//
//    assertAll(() ->
//      assertDoesNotThrow(() -> {
//        UserEntity result = memberService.join(request);
//        assertThat(request.getName()).isEqualTo(result.getName());
//      })
//    );
//  }
//
//  @Test
//  @DisplayName("회원 정보 조회 테스트")
//  void findOne() {
//    UserEntity user = userRepository.save(사용자());
//
//    assertAll(() ->
//      assertDoesNotThrow(() -> {
//        UserEntity result = memberService.findOne(user.getId());
//        assertThat(result.getName()).isEqualTo(user.getName());
//        assertThat(result.getId()).isEqualTo(user.getId());
//      })
//    );
//  }
//
//  @Test
//  @DisplayName("회원 정보 삭제 테스트")
//  void deleteOne() {
//    UserEntity user = userRepository.save(사용자());
//
//    assertAll(() ->
//      assertDoesNotThrow(() -> {
//        memberService.deleteOne(user.getId());
//      })
//    );
//
//  }
}
