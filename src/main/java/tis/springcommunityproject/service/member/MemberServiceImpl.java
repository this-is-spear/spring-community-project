package tis.springcommunityproject.service.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.repository.JpaUserRepository;
import tis.springcommunityproject.repository.UserRepository;
import tis.springcommunityproject.service.NotFoundDataException;

@Service
public class MemberServiceImpl implements MemberService{

	private final UserRepository userRepository;

	public MemberServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserEntity join(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public UserEntity edit(Long id, UserEntity user) {
		UserEntity findUser = userRepository.findById(id).orElseThrow(NotFoundDataException::new);
		findUser.updateName(user.getName());
		return findUser;
	}

	@Override
	@Transactional(readOnly = true)
	public UserEntity findOne(Long id) {
		return userRepository.findById(id).orElseThrow(NotFoundDataException::new);
	}

	@Override
	@Transactional
	public void deleteOne(Long id) {
		userRepository.deleteById(id);
	}
}
