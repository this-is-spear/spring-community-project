package tis.springcommunityproject.service.community;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tis.springcommunityproject.domain.community.BoardPostEntity;
import tis.springcommunityproject.repository.BoardPostRepository;
import tis.springcommunityproject.repository.JpaBoardPostRepository;
import tis.springcommunityproject.service.AuthenticationException;
import tis.springcommunityproject.service.member.MemberService;
import tis.springcommunityproject.service.NotFoundDataException;

import java.util.Objects;

import static com.google.common.base.Preconditions.*;

@Service
public class CommunityServiceImpl implements CommunityService {

	private final BoardPostRepository postRepository;
	private final MemberService memberService;

	public CommunityServiceImpl(BoardPostRepository postRepository, MemberService memberService) {
		this.postRepository = postRepository;
		this.memberService = memberService;
	}

	@Override
	@Transactional
	public BoardPostEntity create(Long boardId, BoardPostEntity post, Long authId) {
		checkArgument(boardId != null, "Board id is Provided");
		checkArgument(post != null, "Post is Provided");
		checkArgument(authId != null, "Auth id is Provided");

		post.updateUser(memberService.findOne(authId));
		return postRepository.save(post);
	}

	@Override
	@Transactional(readOnly = true)
	public BoardPostEntity findOne(Long boardId, Long postId, Long authId) {
		checkArgument(boardId != null, "Board id is Provided");
		checkArgument(postId != null, "Post id is Provided");
		checkArgument(authId != null, "Auth id is Provided");
		BoardPostEntity findPost = postRepository.findById(postId).orElseThrow(NotFoundDataException::new);

		checkArgument(findPost.getUser().getId().equals(authId), "인증되지 않은 사용자입니다.");

		return findPost;
	}

	@Override
	@Transactional
	public BoardPostEntity updateOne(Long boardId, Long postId, BoardPostEntity request, Long authId) {
		checkArgument(boardId != null, "Board id is Provided");
		checkArgument(postId != null, "Post id is Provided");
		checkArgument(authId != null, "Auth id is Provided");

		BoardPostEntity findPost = findOne(boardId, postId, authId);

		if (!findPost.getUser().getId().equals(authId)) {
			throw new AuthenticationException();
		}
		if (validateStringCheck(request.getContent(), findPost.getContent())) {
			findPost.updateContent(request.getContent());
		}
		if (validateStringCheck(request.getTitle(), findPost.getTitle())) {
			findPost.updateTitle(request.getTitle());
		}
		findPost.updateAt();
		BoardPostEntity updatePost = postRepository.save(Objects.requireNonNull(findPost));

		checkArgument(updatePost.getTitle().equals(request.getTitle()));
		checkArgument(updatePost.getContent().equals(request.getContent()));

		return findPost;
	}

	private boolean validateStringCheck(String postString, String findPostString) {
		return !postString.trim().isEmpty() || !postString.equals(findPostString);
	}

	@Override
	@Transactional
	public void deleteOne(Long boardId, Long postId, Long authId) {
		checkArgument(boardId != null, "Board id is Provided");
		checkArgument(postId != null, "Post id is Provided");
		checkArgument(authId != null, "Auth id is Provided");

		BoardPostEntity findPost = findOne(boardId, postId, authId);

		postRepository.deleteById(postId);
	}

}
