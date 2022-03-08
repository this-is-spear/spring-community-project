package tis.springcommunityproject.api.shop;

import tis.springcommunityproject.api.member.UserDto;
import tis.springcommunityproject.domain.InsertionDate;
import tis.springcommunityproject.domain.shop.ShopPostEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ShopPostDto {

	private String title;

	private String content;

	private UserDto user;

	private InsertionDate date;

	private List<ShopReviewDto> shopReview;

	protected ShopPostDto() {
	}

	public ShopPostDto(String title, String content, UserDto user, InsertionDate date, List<ShopReviewDto> shopReview) {
		this.title = title;
		this.content = content;
		this.user = user;
		this.date = date;
		this.shopReview = shopReview;
	}

	public ShopPostDto(ShopPostEntity source) {
		this(source.getTitle(), source.getContent(), new UserDto(source.getUser()), source.getDate(), source.getShowReview().stream().map(ShopReviewDto::new).collect(Collectors.toList()));
	}

	public ShopPostEntity newShopPostEntity() {
		return new ShopPostEntity(title, content);
	}
}
