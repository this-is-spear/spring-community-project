package tis.springcommunityproject.service.memorydatabases;

import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.repository.UserRepository;

import java.util.*;

public class MemoryUserRepository implements UserRepository {

  Map<Long, UserEntity> userEntityHashMap = new HashMap<>();

  private static long sequence = 0L;

  @Override
  public UserEntity save(UserEntity user) {
    long id = ++sequence;
    UserEntity userEntity = UserEntity.of(id, user.getName(), user.getPassword(), user.getArea(), user.isShopOwner());
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

  @Override
  public Optional<UserEntity> findByName(String username) {
    return userEntityHashMap.values().stream()
      .filter(user -> user.getName().equals(username)).findFirst();
  }
}
