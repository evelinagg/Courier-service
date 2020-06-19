
public class CourierWorkerInternational extends CourierWorker {
	public CourierWorkerInternational(String name) {
		super(name);

		this.level = CourierWorker.INTERNATIONAL;
	}

	@Override
	protected void writeMessage() {
		System.out.println("Package has gone with International Courier [" + this.getName() + "]");
	}
}
