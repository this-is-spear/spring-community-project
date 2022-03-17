package tis.springcommunityproject.repository;

import tis.springcommunityproject.domain.UserEntity;

import java.util.Optional;

public interface UserRepository {
  <S extends UserEntity> S save(S entity);

  Optional<UserEntity> findById(Long userId);

  boolean existsById(Long userId);

  void deleteById(Long userId);
}
