package tis.communityproject.service.member;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tis.communityproject.domain.UserEntity;
import tis.communityproject.error.NotFoundDataException;
import tis.communityproject.repository.JpaUserRepository;

@Service
public class MemberServiceImpl implements MemberService{

	private final JpaUserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public MemberServiceImpl(JpaUserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public UserEntity join(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public UserEntity updateJoin(UserEntity user) {
		UserEntity userEntity = UserEntity.of(user.getName(), passwordEncoder.encode(user.getPassword()));
		return userRepository.save(userEntity);
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
