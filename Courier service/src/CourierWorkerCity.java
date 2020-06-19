
public class CourierWorkerCity extends CourierWorker {
	public CourierWorkerCity(String name) {
		super(name);

		this.level = CourierWorker.CITY;
	}

	@Override
	protected void writeMessage() {
		System.out.println("Package has gone with City Courier [" + this.getName() + "]");
	}
}
