package tis.springcommunityproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tis.springcommunityproject.domain.BoardEntity;
import tis.springcommunityproject.domain.PostEntity;
import tis.springcommunityproject.domain.UserEntity;
import tis.springcommunityproject.repository.JpaBoardRepository;
import tis.springcommunityproject.repository.JpaPostRepository;
import tis.springcommunityproject.repository.JpaUserRepository;
import tis.springcommunityproject.service.CommunityService;
import tis.springcommunityproject.service.MemberService;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class DataInit {

	public static long AUTH_ID = 2L;

	private final JpaBoardRepository boardRepository;
	private final CommunityService communityService;
	private final MemberService memberService;

	public DataInit(JpaBoardRepository boardRepository, CommunityService communityService, MemberService memberService) {
		this.boardRepository = boardRepository;
		this.communityService = communityService;
		this.memberService = memberService;
	}

	@PostConstruct
	public void inIt() {
		BoardEntity board = BoardEntity.of(null, null, "test title");
		BoardEntity boardEntity = boardRepository.save(board);
		log.info("creaete boardEntity {}{}", boardEntity.getId(), boardEntity.getTitle());

		UserEntity user = UserEntity.of(null, "name", null);
		UserEntity userEntity = memberService.join(user);
		log.info("creaete userEntity {}{}", userEntity, userEntity.getName());

		PostEntity post = PostEntity.of("test title", "test content");
		PostEntity postEntity = communityService.create(board.getId(), post, AUTH_ID);
		log.info("creaete postEntity {}{}", postEntity.getId(), postEntity.getTitle());

	}

}
