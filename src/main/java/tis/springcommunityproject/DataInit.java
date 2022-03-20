package tis.springcommunityproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tis.springcommunityproject.domain.area.*;
import tis.springcommunityproject.domain.community.BoardEntity;
import tis.springcommunityproject.domain.community.BoardPostEntity;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.domain.shop.ShopPostEntity;
import tis.springcommunityproject.domain.shop.ShopReviewEntity;
import tis.springcommunityproject.repository.JpaBoardRepository;
import tis.springcommunityproject.service.community.CommunityService;
import tis.springcommunityproject.service.member.MemberService;
import tis.springcommunityproject.service.shop.ShopService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Slf4j
@Component
public class DataInit {
	public static long AUTH_ID = 2L;
	private final static Area SEOCHO = new Area(new State("서울시"), new City("서초구"), new Region("서초동"), new Gps(BigDecimal.valueOf(37.4877), BigDecimal.valueOf(127.0174)));
	private final static Area YEOKSAM = new Area(new State("서울시"), new City("강남구"), new Region("역삼동"), new Gps(BigDecimal.valueOf(37.4999), BigDecimal.valueOf(127.0374)));
	private final static Area SAMSUNG = new Area(new State("서울시"), new City("강남구"), new Region("삼성동"), new Gps(BigDecimal.valueOf(37.5140), BigDecimal.valueOf(127.0565)));
	private static final String SHOP = "shop";
	private static final String TEST_TITLE = "test title ";
	private static final String BOARD = "board";
	private static final String TEST_CONTENT = "test content ";
	private static final String NAME = "name";
	private static final String PASSWORD = "password";


	private final JpaBoardRepository boardRepository;
	private final ShopService shopService;
	private final CommunityService communityService;
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;


	public DataInit(JpaBoardRepository boardRepository, ShopService shopService, CommunityService communityService, MemberService memberService, PasswordEncoder passwordEncoder) {
		this.boardRepository = boardRepository;
		this.shopService = shopService;
		this.communityService = communityService;
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void inIt() {
		BoardEntity boardEntity = boardRepository.save(BoardEntity.of(null, null, TEST_TITLE));
		UserEntity userEntity = memberService.join( UserEntity.of(null, NAME, passwordEncoder.encode(PASSWORD), SEOCHO, false));
		BoardPostEntity createBoardPost = communityService.create(boardEntity.getId(), BoardPostEntity.of(TEST_TITLE + BOARD, TEST_CONTENT + BOARD), AUTH_ID);
		ShopPostEntity createShopPost = shopService.createShopPost(AUTH_ID, ShopPostEntity.of(TEST_TITLE + SHOP, TEST_CONTENT + SHOP), AUTH_ID);
		ShopReviewEntity shopReview = ShopReviewEntity.of(TEST_CONTENT);
		shopService.createShopReview(createShopPost.getId(), shopReview, AUTH_ID);
	}

}
