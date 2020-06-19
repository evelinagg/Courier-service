public abstract class CourierWorker implements ICourierWorker {

	// Observer
	private String name;
	private ICourierTeamster assignedTeamster;

	// Chain of Responsibility
	public static int CITY = 1;
	public static int COUNTRY = 2;
	public static int INTERNATIONAL = 3;

	protected int level;
	protected CourierWorker nextWorker;

	// State
	public Context courierStateContext = new Context();

	public Package currentPackage;

	public CourierWorker(String name) {
		this.name = name;

		// Every worker starts out in an idle state
		this.courierStateContext.setState(new IdleCourierState());
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return this.level;
	}

	public IState getState() {
		return this.courierStateContext.getState();
	}

	public void applyState(IState state, boolean notifyTeamster) {
		this.courierStateContext.setState(state);

		System.out.println(this.getName() + " is currently in " + state.getStateName());

		if (this.courierStateContext.getState().getClass().equals(WorkingCourierState.class)) {
			System.out.println(
					this.getName() + " is currently preparing package [" + this.currentPackage.getName() + "]");
		}

		if (notifyTeamster) {
			this.assignedTeamster.notifyTeamster();
		}
	}

	public void setNextWorker(ICourierWorker nextWorker) {
		this.nextWorker = (CourierWorker) nextWorker;
	}

	public boolean sendPackage(int level, IPackage package1) {
		if (level < 1 || level > 3) {
			System.out.println("Level of package must be between 1 - 3");
			return false;
		}

		if (this.level <= level) {
			this.writeMessage();
		}

		if (this.nextWorker != null) {
			this.nextWorker.sendPackage(level, package1);
		}

		if (this.level == level) {
			package1.setAssignedWorker(this);
		}

		return true;
	}

	@Override
	public void update() {
		if (assignedTeamster == null) {
			System.out.println("No teamster assigned, preparation closed");
			return;
		}

		if (currentPackage == null) {
			System.out.println("Courier [" + getName() + "] has no package, preparation closed");
			return;
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Assigned teamster " + "[" + this.assignedTeamster.getUpdate() + "]" + " has taken package "
				+ "[" + currentPackage.getName() + "]" + " from worker " + "[" + this.getName() + "]");

		cleanUpPackageResponsibility();
	}

	@Override
	public void setTeamster(ICourierTeamster teamster) {
		this.assignedTeamster = teamster;
	}

	@Override
	public void preparePackage(Package package1) {
		this.currentPackage = package1;
		this.applyState(new WorkingCourierState(), this.assignedTeamster.isAutomaticNotify());
	}

	private void cleanUpPackageResponsibility() {
		this.applyState(new IdleCourierState(), this.assignedTeamster.isAutomaticNotify());

		this.currentPackage = null;
	}

	abstract protected void writeMessage();
}
