
public interface ICourierWorker {

	//State
	public IState getState();

	public void applyState(IState state, boolean notifyTeamster);

	// Observer
	public void update();

	public void setTeamster(ICourierTeamster topic);

	public void preparePackage(Package package1);

	//Chain of Responsibility
	public void setNextWorker(ICourierWorker nextWorker);

	public boolean sendPackage(int level, IPackage package1);

	public int getLevel();
}
