// Observable

public interface ICourierTeamster {

	public String getName();

	public void setName(String name);

	public boolean isAutomaticNotify();

	public void setAutomaticNotify(boolean automaticNotify);

	public void subscribe(ICourierWorker observer);

	public void unsubscribe(ICourierWorker observer);

	public void notifyTeamster();

	public String getUpdate();
}
