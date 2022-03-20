package tis.communityproject.config;

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
      SecurityContextHolder.getContext().setAuthentication(new CustomAuthentication());
      log.debug("[{}][{}] : SecurityContextHolder get context", this.getClass().getSimpleName(), SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
      log.debug("[{}][{}] : connected user", this.getClass().getSimpleName(), SecurityContextHolder.getContext().getAuthentication().getName());
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
