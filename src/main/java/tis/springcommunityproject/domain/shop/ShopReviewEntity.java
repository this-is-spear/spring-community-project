package tis.springcommunityproject.domain.shop;

import tis.springcommunityproject.domain.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "shop_review")
public class ShopReviewEntity {

	@Id
	@GeneratedValue
	@Column(name = "shop_review_id")
	private Long id;

	private String content;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	protected ShopReviewEntity() {
	}

	public ShopReviewEntity(String content) {
		this(null, content, null);
	}

	public ShopReviewEntity(Long id, String content, UserEntity user) {
		this.id = id;
		this.content = content;
		this.user = user;
	}

	public static ShopReviewEntity of(String content) {
		return new ShopReviewEntity(content);
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public UserEntity getUser() {
		return user;
	}

	public void updateUser(UserEntity user) {
		this.user = user;
	}

	public ShopReviewEntity updateContent(String content) {
		return new ShopReviewEntity(this.id, content, this.user);
	}
}
