package tis.communityproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tis.communityproject.domain.community.BoardPostEntity;

import java.util.Optional;

@Repository
public interface JpaBoardPostRepository extends JpaRepository<BoardPostEntity, Long>, BoardPostRepository {
  @Override
  <S extends BoardPostEntity> S save(S entity);

  @Override
  Optional<BoardPostEntity> findById(Long postId);

  @Override
  void deleteById(Long postId);
}
