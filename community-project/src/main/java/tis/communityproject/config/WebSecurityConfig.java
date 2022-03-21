package tis.communityproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeRequests(authorizeRequests ->
        authorizeRequests.anyRequest().authenticated()
      )
      .oauth2Login(oauth2Login ->
        oauth2Login.loginPage("/oauth2/authorization/likelion-oidc"))
      .oauth2Client(withDefaults());
    return http.build();
  }
}
