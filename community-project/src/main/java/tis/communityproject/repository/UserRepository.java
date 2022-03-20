package tis.communityproject.repository;


import tis.communityproject.domain.UserEntity;

import java.util.Optional;

public interface UserRepository {
  <S extends UserEntity> S save(S entity);

  Optional<UserEntity> findById(Long userId);

  boolean existsById(Long userId);

  void deleteById(Long userId);

  Optional<UserEntity> findByName(String username);

}
