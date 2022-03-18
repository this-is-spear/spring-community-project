package tis.springcommunityproject.service.shop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tis.springcommunityproject.domain.shop.ShopPostEntity;
import tis.springcommunityproject.domain.shop.ShopReviewEntity;
import tis.springcommunityproject.repository.JpaShopPostRepository;
import tis.springcommunityproject.repository.JpaShopReviewRepository;
import tis.springcommunityproject.service.AuthenticationException;
import tis.springcommunityproject.service.NotFoundDataException;
import tis.springcommunityproject.service.member.MemberService;

@Service
public class ShopServiceImpl implements ShopService{

	private final JpaShopPostRepository shopPostRepository;
	private final JpaShopReviewRepository shopReviewRepository;
	private final MemberService memberService;

	public ShopServiceImpl(JpaShopPostRepository shopPostRepository, JpaShopReviewRepository shopReviewRepository, MemberService memberService) {
		this.shopPostRepository = shopPostRepository;
		this.shopReviewRepository = shopReviewRepository;
		this.memberService = memberService;
	}

	@Override
	@Transactional
	public ShopPostEntity createShopPost(Long managerId, ShopPostEntity post, Long authId) {
		checkValidate(managerId != null, "Manager id is provided");
		checkValidate(authId != null, "Auth id is provided");

		post.updateUser(memberService.findOne(authId));

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
	public ShopPostEntity findShopPost(Long managerId, Long postId, Long authId) {
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
	public ShopPostEntity updateShopPost(Long managerId, Long postId, ShopPostEntity post, Long authId) {
		checkValidate(managerId != null, "Manager id must be provided");
		checkValidate(postId != null, "Post id must be provided");
		checkValidate(authId != null, "Auth id must be provided");

		if (!managerId.equals(authId)) {
			throw new AuthenticationException();
		}

		ShopPostEntity findPost = shopPostRepository.findById(postId).orElseThrow(NotFoundDataException::new);

		checkValidate(findPost.getUser().getId().equals(authId), "Didn't same authId and postId");

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
	public void deleteShopPost(Long managerId, Long postId, Long authId) {
		checkValidate(managerId != null, "Manager id must be provided");
		checkValidate(authId != null, "Auth id must be provided");

		if (!managerId.equals(authId)) {
			throw new AuthenticationException();
		}

		shopPostRepository.deleteById(postId);
	}

	@Override
	@Transactional
	public ShopReviewEntity createShopReview(Long postId, ShopReviewEntity shopReview, Long authId) {
		checkValidate(authId != null, "Auth id must be provided");
		checkValidate(shopReview != null, "shop review must be provided");

		shopReview.updateUser(memberService.findOne(authId));

		ShopPostEntity findShopPost = shopPostRepository.findById(postId).orElseThrow(NotFoundDataException::new);
		findShopPost.addReview(shopReview);

		return shopReview;
	}

	@Override
	@Transactional
	public ShopReviewEntity updateShopReview(Long postId, Long shopReviewId, ShopReviewEntity shopReview, Long authId) {
		checkValidate(shopReviewId != null, "Shop review id id must be provided");
		checkValidate(authId != null, "Auth id must be provided");
		checkValidate(shopReview != null, "shop review must be provided");

		ShopReviewEntity findShopReview = shopReviewRepository.findById(shopReviewId).orElseThrow(NotFoundDataException::new);

		if (!findShopReview.getUser().getId().equals(authId)) {
			throw new AuthenticationException();
		}

		if (shopReview.getContent() != null && !shopReview.getContent().equals(findShopReview.getContent())) {
			return findShopReview.updateContent(shopReview.getContent());
		}

		return findShopReview;
	}

	@Override
	@Transactional
	public void deleteShopReview(Long managerId, Long shopReviewId, Long authId) {
		if (!managerId.equals(authId)) {
			throw new AuthenticationException();
		}
		shopReviewRepository.deleteById(shopReviewId);
	}

	private void checkValidate(boolean b, String message) {
		if (!b) {
			throw new IllegalArgumentException(message);
		}
	}
}
