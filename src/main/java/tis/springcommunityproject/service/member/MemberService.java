package tis.springcommunityproject.service.member;

import tis.springcommunityproject.domain.UserEntity;

public interface MemberService {
	// 회원 가입
	UserEntity join(UserEntity user);

	UserEntity updateJoin(UserEntity user);

	// 회원 정보 수정
	UserEntity edit(Long id, UserEntity user);

	// 회원 정보 가져오기
	UserEntity findOne(Long id);

	// 회원 정보 삭제
	void deleteOne(Long id);
}
