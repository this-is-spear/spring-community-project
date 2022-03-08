package tis.springcommunityproject.service.shop;

import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.domain.shop.ShopPostEntity;
import tis.springcommunityproject.domain.shop.ShopReviewEntity;

public interface ShopService {
	ShopPostEntity createShopPost(Long managerId, ShopPostEntity post, Long authId);

	// find 포스트
	ShopPostEntity findShopPost(Long managerId, Long postId, Long authId);

	// update 포스트
	ShopPostEntity updateShopPost(Long managerId, Long postId, ShopPostEntity post, Long authId);

	// delete 포스트
	void deleteShopPost(Long managerId, Long postId, Long authId);

	//댓글 달기
	ShopReviewEntity createShopReview(Long postId, ShopReviewEntity shopReview, Long authId);

	//댓글 수정
	ShopReviewEntity updateShopReview(Long postId, Long shopReviewId, ShopReviewEntity shopReview, Long authId);

	//댓글 삭제
	void deleteShopReview(Long managerId, Long shopReviewId, Long authId);

}
