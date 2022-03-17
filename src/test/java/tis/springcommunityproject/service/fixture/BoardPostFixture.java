package tis.springcommunityproject.service.fixture;

import tis.springcommunityproject.domain.community.BoardPostEntity;

import static tis.springcommunityproject.service.fixture.UserFixture.사용자;

public class BoardPostFixture {
  private static final long POST_ID = 1L;
  private static final String UPDATE_TITLE = "update title";
  private static final String UPDATE_CONTENT = "update content";
  private static final String TITLE = "title";
  private static final String CONTENT = "content";

  public static BoardPostEntity 게시글() {
    return BoardPostEntity.of("title", "content");
  }

  public static BoardPostEntity 가져오는_게시글() {
    return BoardPostEntity.of(null, TITLE, CONTENT, 사용자());
  }

  public static BoardPostEntity 수정하려는_게시글() {
    return BoardPostEntity.of(null, UPDATE_TITLE, UPDATE_CONTENT, 사용자());
  }
}
