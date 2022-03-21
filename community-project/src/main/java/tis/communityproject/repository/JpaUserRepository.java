package tis.communityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tis.communityproject.domain.UserEntity;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
	@Override
	<S extends UserEntity> S save(S entity);

	@Override
	Optional<UserEntity> findById(Long aLong);

	@Override
	boolean existsById(Long aLong);

	@Override
	void deleteById(Long aLong);

	@Query("select u from UserEntity u where u.name = :username")
	Optional<UserEntity> findByName(String username);
}
