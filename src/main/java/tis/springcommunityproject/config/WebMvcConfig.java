package tis.springcommunityproject.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Bean
  public FilterRegistrationBean<Filter> cookieCheckFilter() {
    FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
    filterFilterRegistrationBean.setFilter(new CookieCheckFilter());
    filterFilterRegistrationBean.setOrder(1);
    filterFilterRegistrationBean.addUrlPatterns("/*");
    return filterFilterRegistrationBean;
  }
}
