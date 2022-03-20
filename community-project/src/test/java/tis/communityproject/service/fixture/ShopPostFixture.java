package tis.communityproject.service.fixture;


import tis.communityproject.domain.shop.ShopPostEntity;
import tis.communityproject.domain.shop.ShopReviewEntity;

import java.util.ArrayList;
import java.util.List;

import static tis.communityproject.service.fixture.UserFixture.사용자;

public class ShopPostFixture {

  public static final String SHOP_TITLE = "shop title";
  public static final String SHOP_CONTENT = "shop content";
  public static final String REVIEW_CONTENT = "review content";
  public static final String UPDATE_POST_TITLE = "update post title";
  public static final String UPDATE_POST_CONTENT = "update post content";
  public static final String UPDATE_REVIEW_CONTENT = "update review content";

  public static ShopPostEntity SHOP_게시글_생성() {
    return new ShopPostEntity(SHOP_TITLE, SHOP_CONTENT);
  }

  public static ShopPostEntity SHOP_게시글_조회() {
    return new ShopPostEntity(1L, SHOP_TITLE, SHOP_CONTENT, 사용자(), SHOP_댓글_리스트());
  }

  public static ShopPostEntity SHOP_게시글_수정() {
    return new ShopPostEntity(UPDATE_POST_TITLE, UPDATE_POST_CONTENT);
  }

  public static ShopReviewEntity SHOP_댓글_생성() {
    return new ShopReviewEntity(REVIEW_CONTENT);
  }

  public static ShopReviewEntity SHOP_댓글_수정() {
    return new ShopReviewEntity(UPDATE_REVIEW_CONTENT);
  }

  public static ShopReviewEntity SHOP_댓글_조회() {
    return new ShopReviewEntity(1L, REVIEW_CONTENT, 사용자());
  }

  public static List<ShopReviewEntity> SHOP_댓글_리스트() {
    ArrayList<ShopReviewEntity> list = new ArrayList<>();
    list.add(SHOP_댓글_조회());
    list.add(SHOP_댓글_조회());
    list.add(SHOP_댓글_조회());
    list.add(SHOP_댓글_조회());
    return list;
  }

}
