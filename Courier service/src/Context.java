
public class Context {
	private IState state;

	public Context() {
	}

	public IState getState() {
		return state;
	}

	public void setState(IState state) {
		this.state = state;
	}
}
