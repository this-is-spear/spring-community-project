package tis.communityproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tis.communityproject.domain.shop.ShopPostEntity;

@Repository
public interface JpaShopPostRepository extends JpaRepository<ShopPostEntity, Long> {
}
