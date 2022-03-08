package tis.springcommunityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tis.springcommunityproject.domain.community.BoardPostEntity;

public interface JpaBoardPostRepository extends JpaRepository<BoardPostEntity, Long> {
}
