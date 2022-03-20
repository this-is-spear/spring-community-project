package tis.springcommunityproject.service.member;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.repository.UserRepository;

import java.util.ArrayList;

@Service
public class CommunityUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  public CommunityUserDetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("no user"));
    return new User(username, user.getPassword(), new ArrayList<>());
  }
}
