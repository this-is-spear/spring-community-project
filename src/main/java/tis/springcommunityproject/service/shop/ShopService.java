package tis.springcommunityproject.service.shop;

import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.domain.shop.ShopPostEntity;

public interface ShopService {
	ShopPostEntity create(Long managerId, ShopPostEntity post, Long authId);

	// find 포스트
	ShopPostEntity findOne(Long managerId, Long postId, Long authId);

	// update 포스트
	ShopPostEntity updateOne(Long managerId, Long postId, ShopPostEntity post, Long authId);

	// delete 포스트
	void deleteOne(Long managerId, Long postId, Long authId);
}
