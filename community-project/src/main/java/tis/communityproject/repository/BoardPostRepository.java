package tis.communityproject.repository;


import tis.communityproject.domain.community.BoardPostEntity;

import java.util.Optional;

public interface BoardPostRepository{

  <S extends BoardPostEntity> S save(S post);

  Optional<BoardPostEntity> findById(Long postId);

  void deleteById(Long postId);
}
