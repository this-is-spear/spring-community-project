package tis.springcommunityproject.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
      .and()
      .authorizeRequests()
      .antMatchers("/", "/**")
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .formLogin()
      .permitAll()
      .and()
      .logout()
      .logoutUrl("logout")
      .permitAll();
  }
}
