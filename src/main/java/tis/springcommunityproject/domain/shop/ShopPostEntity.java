package tis.springcommunityproject.domain.shop;

import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.community.BoardPostEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("SHOP")
public class ShopPostEntity extends PostEntity {

	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "post_id")
	private List<ShopReviewEntity> shopReview;

	protected ShopPostEntity() {
	}

	public ShopPostEntity(Long id, String title, String content, UserEntity user, List<ShopReviewEntity> shopReview) {
		super(id, title, content, user);
		this.shopReview = shopReview;
	}

	public ShopPostEntity(String title, String content) {
		this(null, title, content, null, null);
	}

	public List<ShopReviewEntity> getShowReview() {
		return shopReview;
	}

}
