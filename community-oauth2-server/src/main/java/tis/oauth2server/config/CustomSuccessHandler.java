package tis.oauth2server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  public static final String LOGIN_COOKIE = "likelion_login_cookie";
  public static final String LIKELION = "likelion";

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    log.info("-----------{Add Cookie}----------");
    Cookie cookie = new Cookie(LOGIN_COOKIE, URLEncoder.encode(LIKELION + "_" + objectMapper.writeValueAsString(authentication), StandardCharsets.UTF_8));
    cookie.setMaxAge(60*60);
    response.addCookie(cookie);
    cookie.setDomain("127.0.0.1/**");
    cookie.setPath("/**");
    super.onAuthenticationSuccess(request, response, authentication);
  }
}
