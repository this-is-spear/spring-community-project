package tis.springcommunityproject.service;

import tis.springcommunityproject.domain.PostEntity;

public interface CommunityService {
	// create 포스트
	PostEntity create(Long boardId, PostEntity post, Long authId);

	// find 포스트
	PostEntity findOne(Long boardId, Long postId, Long authId);

	// update 포스트
	PostEntity updateOne(Long boardId, Long postId, PostEntity post, Long authId);

	// delete 포스트
	void deleteOne(Long boardId, Long postId, Long authId);
}
