package tis.communityproject.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Embeddable
public class InsertionDate {
	@Column(updatable = false, nullable = false)
	private LocalDateTime createAt;

	@Column(insertable = false)
	private LocalDateTime updateAt;

	protected InsertionDate() {
	}

	private InsertionDate(LocalDateTime createAt, LocalDateTime updateAt) {
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public static InsertionDate of(LocalDateTime createAt) {
		return new InsertionDate(createAt, null);
	}

	public void changeUpdateAt() {
		this.updateAt = now();
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
}
