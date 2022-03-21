package tis.communityproject.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import tis.communityproject.domain.UserEntity;
import tis.communityproject.service.member.MemberService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/request-login")
  public String loginPage(HttpServletRequest request) {
    log.info("request = {}", request.getRequestURI());
    log.info("request = {}", request.getServletPath());

    return "redirect:localhost:10000/user/login?" + request.getServletPath() ;
  }

}
