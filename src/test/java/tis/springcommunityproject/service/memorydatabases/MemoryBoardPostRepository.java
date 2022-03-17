package tis.springcommunityproject.service.memorydatabases;

import tis.springcommunityproject.domain.community.BoardPostEntity;
import tis.springcommunityproject.repository.BoardPostRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class MemoryBoardPostRepository implements BoardPostRepository {

  Map<Long, BoardPostEntity> boardPostEntityMap = new HashMap<>();

  private static long sequence = 0L;


  @Override
  public BoardPostEntity save(BoardPostEntity post) {
    long id = ++sequence;
    BoardPostEntity boardPost = BoardPostEntity.of(id, post.getTitle(), post.getContent(), post.getUser());
    boardPostEntityMap.put(id, boardPost);
    return boardPost;
  }

  @Override
  public Optional<BoardPostEntity> findById(Long postId) {
    return Optional.ofNullable(boardPostEntityMap.get(postId));
  }

  @Override
  public void deleteById(Long postId) {
    boardPostEntityMap.remove(postId);
  }
}
