public class CourierServiceMain {
	public static void main(String[] args) {
		OneByOneApproach();
	}

	private static void OneByOneApproach() {
		CourierTeamster teamster = new CourierTeamster("John");
		ICourierWorker worker = (CourierWorkerCity) getChain(teamster);

		//3 - International, 2 - Country, 1 - City
		IPackage package1 = new Package("Nothing Suspicious Stuff", worker, 3);
		IPackage package2 = new Package("Server Stuff", worker, 2);

		package1.process();
		package2.process();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void StateFirstApproach() {
		CourierTeamster teamster = new CourierTeamster("Pesho");
		teamster.setAutomaticNotify(false);

		ICourierWorker worker = (CourierWorkerCity) getChain(teamster);

		// 3 - International, 2 - Country, 1 - City
		IPackage package1 = new Package("Nothing Suspicious Stuff", worker, 3);
		IPackage package2 = new Package("Server Stuff", worker, 2);

		package1.process();
		package2.process();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		teamster.notifyTeamster();
	}

	private static ICourierWorker getChain(CourierTeamster teamster) {

		ICourierWorker cityWorker = new CourierWorkerCity("John");
		ICourierWorker countryWorker = new CourierWorkerCountry("Josh");
		ICourierWorker internationalWorker = new CourierWorkerInternational("Ivan");

		teamster.subscribe(cityWorker);
		teamster.subscribe(countryWorker);
		teamster.subscribe(internationalWorker);

		cityWorker.setNextWorker(countryWorker);
		countryWorker.setNextWorker(internationalWorker);

		return cityWorker;
	}
}
