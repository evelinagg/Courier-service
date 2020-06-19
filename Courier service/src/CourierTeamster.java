import java.util.ArrayList;

public class CourierTeamster implements ICourierTeamster {
	private ArrayList<ICourierWorker> workers = new ArrayList<ICourierWorker>();
	private boolean automaticNotify = true;
	private String name;

	public CourierTeamster(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAutomaticNotify() {
		return automaticNotify;
	}

	public void setAutomaticNotify(boolean automaticNotify) {
		this.automaticNotify = automaticNotify;
	}

	@Override
	public void subscribe(ICourierWorker observer) {
		this.workers.add(observer);
		observer.setTeamster(this);
	}

	@Override
	public void unsubscribe(ICourierWorker observer) {
		this.workers.remove(observer);
	}

	@Override
	public void notifyTeamster() {
		for (ICourierWorker worker : this.workers) {
			if (worker.getState().getClass().equals(WorkingCourierState.class)) {
				worker.update();
			}
		}
	}

	@Override
	public String getUpdate() {
		return this.name;
	}
}
