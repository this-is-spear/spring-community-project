package tis.springcommunityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tis.springcommunityproject.domain.shop.ShopPostEntity;

public interface JpaShopPostRepository extends JpaRepository<ShopPostEntity, Long> {
}
