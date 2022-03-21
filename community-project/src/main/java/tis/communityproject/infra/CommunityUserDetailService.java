package tis.communityproject.infra;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tis.communityproject.domain.UserEntity;
import tis.communityproject.repository.JpaUserRepository;

import java.util.ArrayList;

import static java.lang.String.format;

//@Service
public class CommunityUserDetailService implements UserDetailsService {

  private final JpaUserRepository userRepository;

  public CommunityUserDetailService(JpaUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository
      .findByName(username)
      .orElseThrow(
        () -> new UsernameNotFoundException(
          format("User: %s, not found", username)
        )
      );
    return new User(username, user.getPassword(), new ArrayList<>());
  }
}
