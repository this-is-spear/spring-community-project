package tis.springcommunityproject.domain.area;

import javax.persistence.*;

@Embeddable
public class Area {

	@Embedded
	private State state;

	@Embedded
	private City city;

	@Embedded
	private Region region;

	@Embedded
	private Gps gps;

	protected Area() {
	}

	public Area(State state, City city, Region region, Gps gps) {
		this.state = state;
		this.city = city;
		this.region = region;
		this.gps = gps;
	}

	public Area of(State state, City city, Region region, Gps gps) {
		return new Area(state, city, region, gps);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City town) {
		this.city = town;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region siGunGu) {
		this.region = siGunGu;
	}

	public Gps getGps() {
		return gps;
	}

	public void setGps(Gps gps) {
		this.gps = gps;
	}
}
