package tis.springcommunityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tis.springcommunityproject.domain.community.BoardEntity;

import java.util.Optional;

public interface JpaBoardRepository extends JpaRepository<BoardEntity, Long> {
	@Override
	<S extends BoardEntity> S save(S entity);

	@Override
	Optional<BoardEntity> findById(Long aLong);

	@Override
	boolean existsById(Long aLong);

	@Override
	void deleteById(Long aLong);

}
