package tis.springcommunityproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.repository.JpaPostRepository;

import java.util.Objects;

@Service
public class CommunityServiceImpl implements CommunityService {

	private final JpaPostRepository postRepository;
	private final MemberService memberService;

	public CommunityServiceImpl(JpaPostRepository postRepository, MemberService memberService) {
		this.postRepository = postRepository;
		this.memberService = memberService;
	}

	@Override
	@Transactional
	public PostEntity create(Long boardId, PostEntity post, Long authId) {
		post.updateUser(memberService.findOne(authId));
		return postRepository.save(post);
	}

	@Override
	@Transactional(readOnly = true)
	public PostEntity findOne(Long boardId, Long postId, Long authId) {
		PostEntity findPost = postRepository.findById(postId).orElseThrow(NotFoundDataException::new);
		if (!findPost.getUser().getId().equals(authId)) {
			throw new IllegalArgumentException();
		}
		return findPost;
	}

	@Override
	@Transactional
	public PostEntity updateOne(Long boardId, Long postId, PostEntity post, Long authId) {
		PostEntity findPost = findOne(boardId, postId, authId);

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
		PostEntity updatePost = postRepository.save(Objects.requireNonNull(findPost));
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
		PostEntity post = findOne(boardId, postId, authId);
		if (!post.getUser().getId().equals(authId)) {
			throw new AuthenticationException();
		}
		postRepository.deleteById(postId);
	}

}
