package tis.springcommunityproject.api.shop;

import tis.springcommunityproject.api.member.UserDto;
import tis.springcommunityproject.domain.InsertionDate;
import tis.springcommunityproject.domain.shop.ShopPostEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShopPostDto {

	private String title;

	private String content;

	private UserDto user;

	private InsertionDate date;

	private List<ShopReviewDto> shopReview = new ArrayList<>();

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	public InsertionDate getDate() {
		return date;
	}

	public List<ShopReviewDto> getShopReview() {
		return Collections.unmodifiableList(shopReview);
	}
}
