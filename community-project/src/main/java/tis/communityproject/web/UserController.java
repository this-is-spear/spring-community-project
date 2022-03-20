package tis.communityproject.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import tis.communityproject.domain.UserEntity;
import tis.communityproject.service.member.MemberService;

@Controller
@RequestMapping("user")
public class UserController {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final MemberService memberService;

  public UserController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("login")
  public String loginPage() {
    return "login";
  }

  @GetMapping("signup")
  public String signUpPage() {
    return "sign-up";
  }

  @PostMapping("signup")
  public String signUp(
    @RequestParam String username,
    @RequestParam String password,
    @RequestParam String passwordConfirm
  ) {
    if (!password.equals(passwordConfirm)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    UserEntity entity = memberService.updateJoin(UserEntity.of(username, password));
    log.info("join success {}", entity);
    return "redirect:/";
  }

}
