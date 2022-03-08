package tis.springcommunityproject.service.shop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tis.springcommunityproject.domain.shop.ShopPostEntity;
import tis.springcommunityproject.repository.JpaShopPostRepository;
import tis.springcommunityproject.repository.JpaUserRepository;
import tis.springcommunityproject.service.AuthenticationException;
import tis.springcommunityproject.service.NotFoundDataException;

@Service
public class ShopServiceImpl implements ShopService{

	private final JpaShopPostRepository shopPostRepository;
	private final JpaUserRepository userRepository;

	public ShopServiceImpl(JpaShopPostRepository shopPostRepository, JpaUserRepository userRepository) {
		this.shopPostRepository = shopPostRepository;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public ShopPostEntity create(Long managerId, ShopPostEntity post, Long authId) {
		checkValidate(managerId != null, "Manager id is provided");
		checkValidate(authId != null, "Auth id is provided");

		if (!managerId.equals(authId)) {
			throw new AuthenticationException();
		}
		ShopPostEntity createPost = shopPostRepository.save(post);
		if (!createPost.getUser().getId().equals(authId)) {
			throw new IllegalArgumentException();
		}
		return createPost;
	}

	@Override
	@Transactional(readOnly = true)
	public ShopPostEntity findOne(Long managerId, Long postId, Long authId) {
		checkValidate(managerId != null, "Manager id is provided");
		checkValidate(authId != null, "Auth id is provided");

		ShopPostEntity post = shopPostRepository.findById(postId).orElseThrow(NotFoundDataException::new);
		if (!post.getUser().getId().equals(managerId)) {
			throw new IllegalArgumentException();
		}
		return post;
	}

	@Override
	@Transactional
	public ShopPostEntity updateOne(Long managerId, Long postId, ShopPostEntity post, Long authId) {
		checkValidate(managerId != null, "Manager id is provided");
		checkValidate(postId != null, "Post id is provided");
		checkValidate(authId != null, "Auth id is provided");

		if (!managerId.equals(authId)) {
			throw new AuthenticationException();
		}

		ShopPostEntity findPost = shopPostRepository.findById(postId).orElseThrow(NotFoundDataException::new);

		checkValidate(findPost.getUser().getId().equals(authId), "not same authId and postId");

		if (post.getTitle() != null) {
			findPost.updateTitle(post.getTitle());
		}

		if (post.getContent() != null) {
			findPost.updateContent(post.getContent());
		}

		findPost.updateAt();

		return findPost;
	}

	@Override
	@Transactional
	public void deleteOne(Long managerId, Long postId, Long authId) {
		checkValidate(managerId != null, "Manager id is provided");
		checkValidate(authId != null, "Auth id is provided");

		if (!managerId.equals(authId)) {
			throw new AuthenticationException();
		}

		shopPostRepository.deleteById(postId);
	}

	private void checkValidate(boolean b, String message) {
		if (!b) {
			throw new IllegalArgumentException(message);
		}
	}
}
