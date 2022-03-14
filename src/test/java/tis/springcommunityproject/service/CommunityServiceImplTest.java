package tis.springcommunityproject.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.community.BoardPostEntity;
import tis.springcommunityproject.repository.JpaBoardPostRepository;
import tis.springcommunityproject.service.community.CommunityServiceImpl;
import tis.springcommunityproject.service.member.MemberService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tis.springcommunityproject.service.fixture.BoardPostFixture.가져오는_게시글;
import static tis.springcommunityproject.service.fixture.BoardPostFixture.게시글;
import static tis.springcommunityproject.service.fixture.UserFixture.사용자;

@ExtendWith(MockitoExtension.class)
class CommunityServiceImplTest {
	public static final long BOARD_ID = 1L;
	public static final long POST_ID = 1L;
	public static final long AUTH_ID = 1L;
	public static final String UPDATE_TITLE = "update title";
	public static final String UPDATE_CONTENT = "update content";
	@Mock
	private JpaBoardPostRepository postRepository;

	@Mock
	private MemberService memberService;

	@InjectMocks
	private CommunityServiceImpl communityService;

	UserEntity user;
	BoardPostEntity post;

	@BeforeEach
	void setUp() {
		user = 사용자();
		post = 가져오는_게시글();
	}

	//포스트 생성
	@Test
	@Order(1)
	@DisplayName("포스트 생성 테스트")
	void createPostTest() {
		BoardPostEntity request = 게시글();

		when(memberService.findOne(any())).thenReturn(user);
		when(postRepository.save(any())).thenReturn(request);

		BoardPostEntity createPost = communityService.create(BOARD_ID, request, AUTH_ID);

		assertThat(createPost.getTitle()).isEqualTo(post.getTitle());
		assertThat(createPost.getContent()).isEqualTo(post.getContent());
	}

	//포스트 조회
	@Test
	@Order(2)
	@DisplayName("포스트 조회 테스트")
	void findPostTest() {
		when(postRepository.findById(any())).thenReturn(Optional.of(post));

		PostEntity findPost = communityService.findOne(BOARD_ID, POST_ID, AUTH_ID);

		assertThat(findPost).isEqualTo(post);
	}

	//포스트 삭제
	@Test
	@Order(3)
	@DisplayName("포스트 삭제 테스트")
	void deletePostTest() {
		when(postRepository.findById(any())).thenReturn(Optional.ofNullable(post));
		doNothing().when(postRepository).deleteById(any());
		communityService.deleteOne(BOARD_ID, POST_ID, AUTH_ID);
	}

	//포스트 업데이트
	@Test
	@Order(4)
	@DisplayName("포스트 업데이트 테스트")
	void updatePostTest() {
		BoardPostEntity postRequest = BoardPostEntity.of(null, UPDATE_TITLE, UPDATE_CONTENT, user);
		when(postRepository.findById(any())).thenReturn(Optional.ofNullable(post));

		updatePost(postRequest);

		when(postRepository.save(any())).thenReturn(post);
		PostEntity updatePost = communityService.updateOne(BOARD_ID, POST_ID, postRequest, AUTH_ID);

		assertThat(updatePost.getTitle()).isEqualTo(postRequest.getTitle());
		assertThat(updatePost.getContent()).isEqualTo(postRequest.getContent());
	}

	private void updatePost(PostEntity postRequest) {
		post.updateTitle(postRequest.getTitle());
		post.updateContent(postRequest.getContent());
	}

}
