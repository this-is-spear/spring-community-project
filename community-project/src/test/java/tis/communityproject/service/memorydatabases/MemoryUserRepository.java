package tis.communityproject.service.memorydatabases;


import tis.communityproject.domain.UserEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryUserRepository {

  Map<Long, UserEntity> userEntityHashMap = new HashMap<>();

  private static long sequence = 0L;

  public UserEntity save(UserEntity user) {
    long id = ++sequence;
    UserEntity userEntity = UserEntity.of(id, user.getName(), user.getPassword(), user.getArea(), user.isShopOwner());
    userEntityHashMap.put(id, userEntity);
    return userEntity;
  }

  public Optional<UserEntity> findById(Long userId) {
    return Optional.ofNullable(userEntityHashMap.get(userId));
  }

  public void deleteById(Long userId) {
    userEntityHashMap.remove(userId);
  }

  public Optional<UserEntity> findByName(String username) {
    return userEntityHashMap.values().stream()
      .filter(user -> user.getName().equals(username)).findFirst();
  }
}
