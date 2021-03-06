package tis.communityproject.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tis.communityproject.domain.UserEntity;
import tis.communityproject.domain.shop.ShopPostEntity;
import tis.communityproject.domain.shop.ShopReviewEntity;
import tis.communityproject.repository.JpaShopPostRepository;
import tis.communityproject.repository.JpaShopReviewRepository;
import tis.communityproject.service.member.MemberService;
import tis.communityproject.service.shop.ShopServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static tis.communityproject.service.fixture.ShopPostFixture.*;
import static tis.communityproject.service.fixture.UserFixture.사용자;

@ExtendWith(MockitoExtension.class)
class ShopServiceImplTest {

  @Mock
  private JpaShopPostRepository shopPostRepository;

  @Mock
  private JpaShopReviewRepository shopReviewRepository;

  @Mock
  private MemberService memberService;

  @InjectMocks
  private ShopServiceImpl shopService;

  @Test
  @DisplayName("shop 게시글 생성")
  void createShopPost() {
    ShopPostEntity request = SHOP_게시글_생성();
    UserEntity user = 사용자();
    when(memberService.findOne(any())).thenReturn(user);
    when(shopPostRepository.save(request)).thenReturn(SHOP_게시글_조회());
    assertAll(() ->
      assertDoesNotThrow(() -> {
        ShopPostEntity result = shopService.createShopPost(user.getId(), request, user.getId());
        assertThat(result.getUser().getId()).isEqualTo(user.getId());
        assertThat(result.getTitle()).isEqualTo(request.getTitle());
      })
    );
  }

  @Test
  @DisplayName("shop 게시글 조회")
  void findShopPostOne() {
    ShopPostEntity post = SHOP_게시글_조회();
    UserEntity user = 사용자();

    when(shopPostRepository.findById(post.getId())).thenReturn(Optional.of(post));
    assertAll(() ->
      assertDoesNotThrow(() -> {
        ShopPostEntity result = shopService.findShopPost(user.getId(), post.getId(), user.getId());
        assertThat(post.getTitle()).isEqualTo(result.getTitle());
        assertThat(post.getContent()).isEqualTo(result.getContent());
      })
    );
  }

  @Test
  @DisplayName("shop 게시글 수정")
  void editPost() {
    ShopPostEntity request = SHOP_게시글_수정();
    ShopPostEntity post = SHOP_게시글_조회();
    UserEntity user = 사용자();

    when(shopPostRepository.findById(any())).thenReturn(Optional.of(post));

    assertAll(() ->
      assertDoesNotThrow(() -> {
        ShopPostEntity result = shopService.updateShopPost(user.getId(), post.getId(), request, user.getId());
        assertThat(post.getTitle()).isEqualTo(result.getTitle());
        assertThat(post.getContent()).isEqualTo(result.getContent());
      })
    );
  }

  @Test
  @DisplayName("shop 게시글 삭제")
  void deletePost() {
    ShopPostEntity post = SHOP_게시글_조회();
    UserEntity user = 사용자();

    doNothing().when(shopPostRepository).deleteById(any());

    assertAll(() ->
      assertDoesNotThrow(() -> {
        shopService.deleteShopPost(user.getId(), post.getId(), user.getId());
      })
    );
  }

  @Test
  @DisplayName("shop 게시글에 리뷰 생성")
  void createShopReview() {
    ShopPostEntity post = SHOP_게시글_조회();
    UserEntity user = 사용자();
    ShopReviewEntity request = SHOP_댓글_생성();

    when(memberService.findOne(any())).thenReturn(user);
    when(shopPostRepository.findById(any())).thenReturn(Optional.of(post));

    ShopReviewEntity result = shopService.createShopReview(post.getId(), request, user.getId());

    assertAll(
      () -> assertThat(result.getUser().getId()).isEqualTo(user.getId()),
      () -> assertThat(result.getContent()).isEqualTo(request.getContent())
    );
  }

  @Test
  @DisplayName("shop 게시글에 리뷰 수정")
  void updateShopReview() {
    ShopPostEntity post = SHOP_게시글_조회();
    UserEntity user = 사용자();
    ShopReviewEntity request = SHOP_댓글_수정();
    ShopReviewEntity shopReview = SHOP_댓글_조회();

    when(shopReviewRepository.findById(any())).thenReturn(Optional.of(shopReview));

    assertAll(() ->
      assertDoesNotThrow(() -> {
        ShopReviewEntity result = shopService.updateShopReview(post.getId(), shopReview.getId(), request, user.getId());
        assertThat(result.getContent()).isEqualTo(request.getContent());
      })
    );
  }

  @Test
  @DisplayName("shop 게시글에 리뷰 삭제")
  void deleteShopReview() {
    ShopReviewEntity shopReview = SHOP_댓글_조회();
    UserEntity user = 사용자();

    assertAll(() ->
      assertDoesNotThrow(() -> {
        shopService.deleteShopReview(user.getId(), shopReview.getId(), user.getId());
      })
    );
  }
}
