package tis.communityproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tis.communityproject.domain.shop.ShopReviewEntity;

@Repository
public interface JpaShopReviewRepository extends JpaRepository<ShopReviewEntity, Long> {
}
