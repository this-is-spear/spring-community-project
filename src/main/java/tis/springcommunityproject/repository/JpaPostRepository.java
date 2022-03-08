package tis.springcommunityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tis.springcommunityproject.domain.PostEntity;

import java.util.Optional;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
	@Override
	<S extends PostEntity> S save(S entity);

	@Override
	Optional<PostEntity> findById(Long aLong);

	@Override
	boolean existsById(Long aLong);

	@Override
	void deleteById(Long aLong);
}
