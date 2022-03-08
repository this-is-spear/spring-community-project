package tis.springcommunityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tis.springcommunityproject.domain.shop.ShopReviewEntity;

public interface JpaShopReviewRepository extends JpaRepository<ShopReviewEntity, Long> {
}
