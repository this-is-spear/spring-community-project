package tis.springcommunityproject.service.community;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tis.springcommunityproject.domain.community.BoardPostEntity;
import tis.springcommunityproject.repository.JpaBoardPostRepository;
import tis.springcommunityproject.service.AuthenticationException;
import tis.springcommunityproject.service.member.MemberService;
import tis.springcommunityproject.service.NotFoundDataException;

import java.util.Objects;

@Service
public class CommunityServiceImpl implements CommunityService {

	private final JpaBoardPostRepository postRepository;
	private final MemberService memberService;

	public CommunityServiceImpl(JpaBoardPostRepository postRepository, MemberService memberService) {
		this.postRepository = postRepository;
		this.memberService = memberService;
	}

	@Override
	@Transactional
	public BoardPostEntity create(Long boardId, BoardPostEntity post, Long authId) {
		post.updateUser(memberService.findOne(authId));
		return postRepository.save(post);
	}

	@Override
	@Transactional(readOnly = true)
	public BoardPostEntity findOne(Long boardId, Long postId, Long authId) {
		BoardPostEntity findPost = postRepository.findById(postId).orElseThrow(NotFoundDataException::new);
		if (!findPost.getUser().getId().equals(authId)) {
			throw new IllegalArgumentException();
		}
		return findPost;
	}

	@Override
	@Transactional
	public BoardPostEntity updateOne(Long boardId, Long postId, BoardPostEntity post, Long authId) {
		BoardPostEntity findPost = findOne(boardId, postId, authId);

		if (!findPost.getUser().getId().equals(authId)) {
			throw new AuthenticationException();
		}
		if (validateStringCheck(post.getContent(), findPost.getContent())) {
			findPost.updateContent(post.getContent());
		}
		if (validateStringCheck(post.getTitle(), findPost.getTitle())) {
			findPost.updateTitle(post.getTitle());
		}
		findPost.updateAt();
		BoardPostEntity updatePost = postRepository.save(Objects.requireNonNull(findPost));
		if (!updatePost.equals(findPost)) {
			throw new IllegalArgumentException();
		}
		return findPost;
	}

	private boolean validateStringCheck(String postString, String findPostString) {
		return !postString.isEmpty() || !postString.equals(findPostString);
	}

	@Override
	@Transactional
	public void deleteOne(Long boardId, Long postId, Long authId) {
		BoardPostEntity post = findOne(boardId, postId, authId);
		if (!post.getUser().getId().equals(authId)) {
			throw new AuthenticationException();
		}
		postRepository.deleteById(postId);
	}

}
