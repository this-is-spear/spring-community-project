package tis.springcommunityproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tis.springcommunityproject.domain.community.BoardEntity;
import tis.springcommunityproject.domain.community.BoardPostEntity;
import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.shop.ShopPostEntity;
import tis.springcommunityproject.domain.shop.ShopReviewEntity;
import tis.springcommunityproject.repository.JpaBoardRepository;
import tis.springcommunityproject.repository.JpaShopPostRepository;
import tis.springcommunityproject.service.community.CommunityService;
import tis.springcommunityproject.service.member.MemberService;
import tis.springcommunityproject.service.shop.ShopService;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class DataInit {

	public static final String SHOP = "shop";
	public static final String TEST_TITLE = "test title ";
	public static final String BOARD = "board";
	public static final String TEST_CONTENT = "test content ";
	public static final String NAME = "name";
	public static long AUTH_ID = 2L;

	private final JpaBoardRepository boardRepository;

	private final ShopService shopService;
	private final CommunityService communityService;
	private final MemberService memberService;

	public DataInit(JpaBoardRepository boardRepository, ShopService shopService, CommunityService communityService, MemberService memberService) {
		this.boardRepository = boardRepository;
		this.shopService = shopService;
		this.communityService = communityService;
		this.memberService = memberService;
	}

	@PostConstruct
	public void inIt() {
		BoardEntity board = BoardEntity.of(null, null, TEST_TITLE);
		BoardEntity boardEntity = boardRepository.save(board);

		UserEntity user = UserEntity.of(null, NAME, null);
		UserEntity userEntity = memberService.join(user);

		BoardPostEntity boardPost = BoardPostEntity.of(TEST_TITLE + BOARD, TEST_CONTENT + BOARD);
		BoardPostEntity createBoardPost = communityService.create(boardPost.getId(), boardPost, AUTH_ID);

		ShopPostEntity shopPost = ShopPostEntity.of(TEST_TITLE + SHOP, TEST_CONTENT + SHOP);
		ShopPostEntity createShopPost = shopService.createShopPost(AUTH_ID, shopPost, AUTH_ID);

		ShopReviewEntity shopReview = ShopReviewEntity.of(TEST_CONTENT);
		shopService.createShopReview(createShopPost.getId(), shopReview, AUTH_ID);

	}

}
