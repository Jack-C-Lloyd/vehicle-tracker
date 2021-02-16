package CI646;

import CI646.tracker.VehicleFactory;
import CI646.tracker.VehicleTracker;
import CI646.tracker.in.GuardedGPSReceiver;
import CI646.tracker.out.VehicleGUI;
import CI646.tracker.in.Receiver;
import CI646.tracker.in.TargetReceiver;

import java.awt.Rectangle;
import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {
		final int numOfVehicles = 10;
		final Rectangle criticalArea = new Rectangle(10, 10, 200, 200);

		VehicleTracker tracker = new VehicleTracker(VehicleFactory.getVehicles(numOfVehicles));
		new VehicleGUI(tracker);

		Semaphore semaphore = new Semaphore(3);

		for (int i = 0; i < numOfVehicles; i++) {
			Receiver receiver = new GuardedGPSReceiver(tracker, VehicleFactory.PREFIX + i, criticalArea, semaphore);
			receiver.start();
		}
		
		new TargetReceiver(tracker).start();
	}
}
