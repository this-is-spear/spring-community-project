package tis.communityproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

  public static final String LOGIN_COOKIE = "likelion_login_cookie";
  public static final String LIKELION = "likelion";

  ObjectMapper objectMapper = new ObjectMapper();

  private static final String LOG_ID = "logId";
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    MDC.put(LOG_ID, UUID.randomUUID().toString());
    log.debug("[{}][{}] REQUEST", MDC.get(LOG_ID), requestURI);

    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      log.info("cookie = {} {}", cookie.getName(), cookie.getValue());
    }

    Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals(LOGIN_COOKIE)).findFirst().orElse(null);

    if (cookie == null) {
      log.debug("[{}][{}] cookie does not exist", MDC.get(LOG_ID), requestURI);
      log.debug("[{}][{}] RESPONSE", MDC.get(LOG_ID), requestURI);
      chain.doFilter(request, response);
      return;
    }

    if (cookie.getValue() == null || !cookie.getValue().contains(LIKELION)) {
      log.debug("[{}][{}] not validate", MDC.get(LOG_ID), requestURI);
      log.debug("[{}][{}] RESPONSE", MDC.get(LOG_ID), requestURI);
      chain.doFilter(request, response);
      return;
    }

    try {
      final String token = cookie.getValue().trim().split("_")[1];
      Authentication authentication = objectMapper.readValue(token, Authentication.class);
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
        authentication.getPrincipal(), null, authentication == null ? List.of() : authentication.getAuthorities()
      );
      auth.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request)
      );
      SecurityContextHolder.getContext().setAuthentication(auth);
      log.debug("[{}][{}] : SecurityContextHolder get context", this.getClass().getSimpleName(), SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
      log.debug("[{}][{}] : connected user", this.getClass().getSimpleName(), SecurityContextHolder.getContext().getAuthentication().getName());
    } finally {
      log.debug("[{}][{}] RESPONSE", MDC.get(LOG_ID), requestURI);
    }
    chain.doFilter(request, response);

  }

  private boolean validate(String token) {
    return token.contains(LIKELION);
  }

}
