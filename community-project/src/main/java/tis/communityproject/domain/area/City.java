package tis.communityproject.domain.area;

import javax.persistence.Embeddable;

@Embeddable
public class City {
	private String town;

	protected City() {}

	public City(String town) {
		this.town = town;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
}
