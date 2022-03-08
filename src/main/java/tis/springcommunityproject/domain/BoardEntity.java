package tis.springcommunityproject.domain;


import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Boards")
public class BoardEntity {
	@Id
	@GeneratedValue
	@Column(name = "board_id")
	private Long id;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "board_id")
	private List<PostEntity> postList;

	private String title;

	protected BoardEntity() {
	}

	public BoardEntity(String title) {
		this(null, null, title);
	}

	private BoardEntity(Long id, List<PostEntity> postList, String title) {
		this.id = id;
		this.postList = postList;
		this.title = title;
	}

	public static BoardEntity of(Long id, List<PostEntity> postList, String title) {
		return new BoardEntity(id, postList, title);
	}

	public Long getId() {
		return id;
	}

	public List<PostEntity> getPostList() {
		return Collections.unmodifiableList(postList);
	}

	public String getTitle() {
		return title;
	}

}
