package CI646.tracker.in;

import CI646.tracker.Vehicle;
import CI646.tracker.VehicleTracker;

import java.util.concurrent.Semaphore;
import java.awt.Rectangle;

public class GuardedGPSReceiver extends Receiver {
	protected Rectangle criticalArea;
	protected Semaphore semaphore;
	protected boolean hasPermission = false;

	public GuardedGPSReceiver(VehicleTracker tracker, String id, Rectangle criticalArea, Semaphore semaphore) {
		super(tracker, id);

		this.criticalArea = criticalArea;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Vehicle v = tracker.getLocation(id);
			Vehicle future = v.move();

			if (inCriticalArea(future)) {
				if (!hasPermission) {
					try {
						semaphore.acquire();
						hasPermission = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				if (hasPermission) {
					semaphore.release();
					hasPermission = false;
				}
			}
			tracker.setLocation(id, v.move());
		}
	}

	private boolean inCriticalArea(Vehicle v) {
		return criticalArea.contains(v.x, v.y);
	}
}
