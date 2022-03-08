package tis.springcommunityproject.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tis.springcommunityproject.service.CommunityService;

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
	public ApiResult<PostDto> createPost(
		@PathVariable Long boardId,
		@ModelAttribute PostDto post
	) {
		return OK(
			new PostDto(
				communityService.create(boardId, post.newPostEntity(), AUTH_ID)
			)
		);
	}

	@GetMapping("/post/{postId}")
	public ApiResult<PostDto> findOne(
		@PathVariable Long boardId,
		@PathVariable Long postId
	) {
		return OK(
			new PostDto(
				communityService.findOne(boardId, postId, AUTH_ID)
			)
		);
	}

	@PatchMapping("/post/{postId}")
	public ApiResult<PostDto> updateOne(
		@PathVariable Long boardId,
		@PathVariable Long postId,
		@ModelAttribute PostDto post
	) {
		return OK(new PostDto(
			communityService.updateOne(boardId, postId, post.newPostEntity(), AUTH_ID)
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

