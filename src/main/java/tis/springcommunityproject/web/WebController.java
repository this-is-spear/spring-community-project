package tis.springcommunityproject.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WebController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }

  @GetMapping
  public String index(Authentication authentication) {

    log.info("connected user : {}", SecurityContextHolder.getContext().getAuthentication().getName());

    return "index";
  }
}
