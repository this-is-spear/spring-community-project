package tis.communityproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tis.communityproject.domain.shop.ShopReviewEntity;

public interface JpaShopReviewRepository extends JpaRepository<ShopReviewEntity, Long> {
}
