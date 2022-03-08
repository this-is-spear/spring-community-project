package tis.springcommunityproject.domain.area;

import javax.persistence.Embeddable;

@Embeddable
public class Town {
	private String town;

	protected Town() {}

	public Town(String town) {
		this.town = town;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
}
