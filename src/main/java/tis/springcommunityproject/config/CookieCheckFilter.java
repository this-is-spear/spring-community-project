package tis.springcommunityproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class CookieCheckFilter implements Filter {

  private static final String LOG_ID = "logId";
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String requestURI = httpServletRequest.getRequestURI();
    MDC.put(LOG_ID, UUID.randomUUID().toString());
    try {
      log.debug("[{}][{}] REQUEST", MDC.get(LOG_ID), requestURI);
      log.debug("[{}][{}] : SecurityContextHolder get context", this.getClass().getSimpleName(), SecurityContextHolder.getContext());
      log.debug("[{}][{}] : connected user", this.getClass().getSimpleName(), SecurityContextHolder.getContext().getAuthentication().getName());
      SecurityContextHolder.getContext().setAuthentication(new CustomAuthentication());
      if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
        log.debug("[{}][{}] : setAuthentication", this.getClass().getSimpleName(), SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
      }
      chain.doFilter(request, response);
    } finally {
      log.debug("[{}][{}] RESPONSE", MDC.get(LOG_ID), requestURI);
    }
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
