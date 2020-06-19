public interface IPackage {
	public void process();

	public void setName(String name);

	public void setAssignedWorker(ICourierWorker assignedWorker);

	public String getName();

	public ICourierWorker getAssignedWorker();
}
