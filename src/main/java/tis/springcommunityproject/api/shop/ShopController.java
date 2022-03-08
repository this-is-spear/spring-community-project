package tis.springcommunityproject.api.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tis.springcommunityproject.api.ApiResult;
import tis.springcommunityproject.service.shop.ShopService;

import static tis.springcommunityproject.DataInit.AUTH_ID;
import static tis.springcommunityproject.api.ApiResult.OK;

@Controller
@ResponseBody
@RequestMapping("/shop/{userId}}")
public class ShopController {
	private final ShopService shopService;

	public ShopController(ShopService shopService) {
		this.shopService = shopService;
	}

	@PostMapping("/post")
	public ApiResult<ShopPostDto> createShopPost(
		@PathVariable Long userId,
		@RequestBody ShopPostDto post
	) {
		return OK(
			new ShopPostDto(
				shopService.create(userId, post.newShopPostEntity(), AUTH_ID)
			)
		);
	}

	@GetMapping("/post/{postId}")
	public ApiResult<ShopPostDto> findShopPost(
		@PathVariable Long userId,
		@PathVariable Long postId
	) {
		return OK(
			new ShopPostDto(
				shopService.findOne(userId, postId, AUTH_ID)
			)
		);
	}

	@PatchMapping("/post/{postId}")
	public ApiResult<ShopPostDto> updateShopPost(
		@PathVariable Long userId,
		@PathVariable Long postId,
		@RequestBody ShopPostDto post
	) {
		return OK(
			new ShopPostDto(
				shopService.updateOne(userId, postId, post.newShopPostEntity(), AUTH_ID)
			)
		);
	}

	@DeleteMapping("/post/{postId}")
	public ApiResult<String> deleteShopPost(
		@PathVariable Long userId,
		@PathVariable Long postId
	) {
		shopService.deleteOne(userId, postId, AUTH_ID);
		return OK("DELETE OK");
	}

}
