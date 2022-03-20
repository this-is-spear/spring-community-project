package tis.communityproject.domain.area;

import javax.persistence.Embeddable;

@Embeddable
public class Region {
	private String siGunGu;

	protected Region() {
	}

	public Region(String siGunGu) {
		this.siGunGu = siGunGu;
	}

	public String getSiGunGu() {
		return siGunGu;
	}

	public void setSiGunGu(String siGunGu) {
		this.siGunGu = siGunGu;
	}
}
