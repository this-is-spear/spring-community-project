package tis.springcommunityproject.domain.area;

import javax.persistence.*;

@Embeddable
public class Area {

	@Embedded
	private State state;

	@Embedded
	private Town town;

	@Embedded
	private SiGunGu siGunGu;

	@Embedded
	private Gps gps;

	protected Area() {
	}

	public Area(State state, Town town, SiGunGu siGunGu, Gps gps) {
		this.state = state;
		this.town = town;
		this.siGunGu = siGunGu;
		this.gps = gps;
	}

	public Area of(State state, Town town, SiGunGu siGunGu, Gps gps) {
		return new Area(state, town, siGunGu, gps);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public SiGunGu getSiGunGu() {
		return siGunGu;
	}

	public void setSiGunGu(SiGunGu siGunGu) {
		this.siGunGu = siGunGu;
	}

	public Gps getGps() {
		return gps;
	}

	public void setGps(Gps gps) {
		this.gps = gps;
	}
}
