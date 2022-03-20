package tis.communityproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tis.communityproject.domain.shop.ShopPostEntity;

public interface JpaShopPostRepository extends JpaRepository<ShopPostEntity, Long> {
}
