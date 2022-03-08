package tis.springcommunityproject.service.community;

import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.domain.community.BoardPostEntity;

public interface CommunityService {
	// create 포스트
	BoardPostEntity create(Long boardId, BoardPostEntity post, Long authId);

	// find 포스트
	BoardPostEntity findOne(Long boardId, Long postId, Long authId);

	// update 포스트
	BoardPostEntity updateOne(Long boardId, Long postId, BoardPostEntity post, Long authId);

	// delete 포스트
	void deleteOne(Long boardId, Long postId, Long authId);
}
