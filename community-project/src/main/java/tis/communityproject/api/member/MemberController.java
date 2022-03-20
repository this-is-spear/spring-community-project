package tis.communityproject.api.member;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tis.communityproject.api.ApiResult;
import tis.communityproject.service.member.MemberService;

import static tis.communityproject.api.ApiResult.OK;

@Controller
@ResponseBody
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	public ApiResult<UserDto> joinMember(
		@RequestBody UserDto userDto
	) {
		return OK(
			new UserDto(
				memberService.join(userDto.newUserEntity())
			)
		);
	}
}
