package tis.oauth2server.user.repo;


import org.springframework.data.repository.CrudRepository;
import tis.oauth2server.user.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    boolean existsByUsername(String username);
}
