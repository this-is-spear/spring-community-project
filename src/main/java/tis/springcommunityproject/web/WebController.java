package tis.springcommunityproject.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WebController {

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }

  @GetMapping
  public String index(Authentication authentication) {
    return "index";
  }
}
