package tis.springcommunityproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.repository.JpaPostRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommunityServiceImplTest {
	public static final long BOARD_ID = 1L;
	public static final long POST_ID = 1L;
	public static final long AUTH_ID = 1L;
	public static final long USER_ID = 1L;
	public static final String TEST_TITLE = "test title";
	public static final String TEST_CONTENT = "test content";
	public static final String USER_NAME = "user";
	public static final String UPDATE_TITLE = "update title";
	public static final String UPDATE_CONTENT = "update content";
	@Mock
	private JpaPostRepository postRepository;

	@Mock
	private MemberService memberService;

	@InjectMocks
	private CommunityServiceImpl communityService;

	UserEntity user;
	PostEntity post;

	@BeforeEach
	void setUp() {
		user = getUser();
		post = getPost(user);
	}

	//포스트 생성
	@Test
	@Order(1)
	@DisplayName("포스트 생성 테스트")
	void createPostTest() {
		when(memberService.findOne(any())).thenReturn(user);
		when(postRepository.save(ArgumentMatchers.any())).thenReturn(post);

		PostEntity createPost = communityService.create(BOARD_ID, post, AUTH_ID);

		assertThat(createPost).isEqualTo(post);
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
		PostEntity postRequest = PostEntity.of(null, UPDATE_TITLE, UPDATE_CONTENT, user, null);
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

	private PostEntity getPost(UserEntity user) {
		return PostEntity.of(POST_ID, TEST_TITLE, TEST_CONTENT, user, null);
	}

	private UserEntity getUser() {
		return UserEntity.of(USER_ID, USER_NAME, null);
	}
}