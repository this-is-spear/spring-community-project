package tis.springcommunityproject.repository;

import tis.springcommunityproject.domain.community.BoardPostEntity;

import java.util.Optional;

public interface BoardPostRepository{

  <S extends BoardPostEntity> S save(S post);

  Optional<BoardPostEntity> findById(Long postId);

  void deleteById(Long postId);
}
