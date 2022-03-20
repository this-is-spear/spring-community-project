package tis.communityproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tis.communityproject.domain.UserEntity;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long>, UserRepository {
	@Override
	<S extends UserEntity> S save(S entity);

	@Override
	Optional<UserEntity> findById(Long aLong);

	@Override
	boolean existsById(Long aLong);

	@Override
	void deleteById(Long aLong);

	Optional<UserEntity> findByName(String username);
}
