package CI646.tracker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import CI646.tracker.Vehicle.DIR;

public class VehicleFactory {

	public static final int MAX_X = 300;
	public static final int MAX_Y = 300;
	public static final int MAX_N = 10;

	public static final String PREFIX = "VEHICLE";

	public static ConcurrentMap<String, Vehicle> getVehicles(int numVehicles) {
		ConcurrentMap<String, Vehicle> result = new ConcurrentHashMap<String, Vehicle>();
		
		for (int i = 0; i < numVehicles; i++) {
			Vehicle p = randomPointNearBottom();
			result.put(PREFIX + i, p);
		}

		return result;
	}

	private static Vehicle randomPointNearBottom() {
		int x = ThreadLocalRandom.current().nextInt(0, MAX_X);
		int y = ThreadLocalRandom.current().nextInt(MAX_Y - 30, MAX_Y);

		return new Vehicle(x, y, randomDir(), randomDir());
	}

	private static DIR randomDir() {
		return ThreadLocalRandom.current().nextInt(2) == 0 ? DIR.NEG : DIR.POS;
	}
}
