package tis.springcommunityproject.service.memorydatabases;

import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {

  Map<Long, UserEntity> userEntityHashMap = new HashMap<>();

  private static long sequence = 0L;

  @Override
  public UserEntity save(UserEntity user) {
    long id = ++sequence;
    UserEntity userEntity = UserEntity.of(id, user.getName(), user.getArea());
    userEntityHashMap.put(id, userEntity);
    return userEntity;
  }

  @Override
  public Optional<UserEntity> findById(Long userId) {
    return Optional.ofNullable(userEntityHashMap.get(userId));
  }

  @Override
  public boolean existsById(Long userId) {
    return userEntityHashMap.containsKey(userId);
  }

  @Override
  public void deleteById(Long userId) {
    userEntityHashMap.remove(userId);
  }
}
