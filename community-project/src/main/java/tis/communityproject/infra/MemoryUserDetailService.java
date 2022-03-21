package tis.communityproject.infra;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MemoryUserDetailService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;

  public MemoryUserDetailService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = User

      .withDefaultPasswordEncoder()
      .username("admin")
      .password("password")
      .build();

    new User("admin", passwordEncoder.encode("password"), new ArrayList<>());
    return user;
  }
}
