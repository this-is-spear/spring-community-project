package tis.springcommunityproject.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final CustomSuccessHandler customSuccessHandler;

  public WebSecurityConfig(UserDetailsService userDetailsService, CustomSuccessHandler customSuccessHandler) {
    this.userDetailsService = userDetailsService;
    this.customSuccessHandler = customSuccessHandler;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/", "/**", "/user/login", "/user/signup")
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .formLogin()
      .loginPage("/user/login")
      .defaultSuccessUrl("/")
//      .successHandler(customSuccessHandler)
      .permitAll()
      .and()
      .logout()
      .logoutUrl("/user/logout")
      .deleteCookies("JSESSIONID")
      .invalidateHttpSession(true)
      .permitAll();
  }
}
