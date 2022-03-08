package tis.springcommunityproject.api.shop;

import tis.springcommunityproject.domain.shop.ShopReviewEntity;

public class ShopReviewDto {

	private String content;

	protected ShopReviewDto() {
	}

	public ShopReviewDto(String content) {
		this.content = content;
	}

	public ShopReviewDto(ShopReviewEntity shopReview) {
		this(shopReview.getContent());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ShopReviewEntity newShopReviewEntity() {
		return new ShopReviewEntity(content);
	}
}
