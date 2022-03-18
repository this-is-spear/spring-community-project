package tis.springcommunityproject.domain.area;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Gps {

	@Column(nullable = false, precision = 16, scale = 10)
	private BigDecimal latitude;
	@Column(nullable = false, precision = 16, scale = 10)
	private BigDecimal longitude;

	protected Gps() {
	}

	public Gps(BigDecimal latitude, BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
}
