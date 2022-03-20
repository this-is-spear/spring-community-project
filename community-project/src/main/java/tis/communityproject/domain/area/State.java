package tis.communityproject.domain.area;

import javax.persistence.Embeddable;

@Embeddable
public class State {
	private String state;

	protected State() {
	}

	public State(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
