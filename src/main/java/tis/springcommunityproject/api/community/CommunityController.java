package tis.springcommunityproject.api.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tis.springcommunityproject.api.ApiResult;
import tis.springcommunityproject.service.community.CommunityService;

import static tis.springcommunityproject.DataInit.AUTH_ID;
import static tis.springcommunityproject.api.ApiResult.*;

@Controller
@ResponseBody
@RequestMapping("/community/{boardId}")
public class CommunityController {

	private final CommunityService communityService;

	public CommunityController(CommunityService communityService) {
		this.communityService = communityService;
	}

	@PostMapping("/post")
	public ApiResult<BoardPostDto> createPost(
		@PathVariable Long boardId,
		@RequestBody BoardPostDto post
	) {
		return OK(
			new BoardPostDto(
				communityService.create(boardId, post.newBoardPostEntity(), AUTH_ID)
			)
		);
	}

	@GetMapping("/post/{postId}")
	public ApiResult<BoardPostDto> findOne(
		@PathVariable Long boardId,
		@PathVariable Long postId
	) {
		return OK(
			new BoardPostDto(
				communityService.findOne(boardId, postId, AUTH_ID)
			)
		);
	}

	@PatchMapping("/post/{postId}")
	public ApiResult<BoardPostDto> updateOne(
		@PathVariable Long boardId,
		@PathVariable Long postId,
		@RequestBody BoardPostDto post
	) {
		return OK(new BoardPostDto(
			communityService.updateOne(boardId, postId, post.newBoardPostEntity(), AUTH_ID)
		));
	}

	@DeleteMapping("/post/{postId}")
	public ApiResult<String> delete(
		@PathVariable Long boardId,
		@PathVariable Long postId
	) {
		communityService.deleteOne(boardId, postId, AUTH_ID);
		return OK("Delete OK");
	}

}

