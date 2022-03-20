package tis.communityproject.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomAuthentication implements Authentication {


  @Override
  public String getName() {
    return "test name";
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
  }

  @Override
  public Object getCredentials() {
    return "credentials";
  }

  @Override
  public Object getDetails() {
    return "details";
  }

  @Override
  public Object getPrincipal() {
    return "principal";
  }

  @Override
  public boolean isAuthenticated() {
    return false;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
  }
}
