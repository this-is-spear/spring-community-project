package tis.springcommunityproject.service.fixture;

import tis.springcommunityproject.domain.community.BoardPostEntity;

public class BoardPostFixture {
  public static final long POST_ID = 1L;


  public static BoardPostEntity 게시글() {
    return BoardPostEntity.of("title", "content");
  }

  public static BoardPostEntity 가져오는_게시글() {
    return BoardPostEntity.of(POST_ID, "title", "content", null);
  }
}
