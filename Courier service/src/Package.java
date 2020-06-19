public class Package implements IPackage {
	private String name;
	private ICourierWorker assignedWorker;
	private int level;

	public Package(String name, ICourierWorker worker, int level) {
		this.name = name;
		this.assignedWorker = worker;
		this.level = level;
	}

	public void process() {
		if (this.assignedWorker.sendPackage(level, this)) {
			this.assignedWorker.preparePackage(this);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ICourierWorker getAssignedWorker() {
		return assignedWorker;
	}

	public void setAssignedWorker(ICourierWorker assignedWorker) {
		this.assignedWorker = assignedWorker;
	}
}
