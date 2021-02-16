package CI646.tracker;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class VehicleTracker {
	private final ConcurrentMap<String, Vehicle> locations;
	private final Map<String, Vehicle> unmodifiableMap;

	private String targetID;

	public VehicleTracker(Map<String, Vehicle> locations) {
		this.locations = new ConcurrentHashMap<String, Vehicle>(locations);
		unmodifiableMap = Collections.unmodifiableMap(this.locations);
	}

	public Map<String, Vehicle> getLocations() {
		return unmodifiableMap;
	}

	public Vehicle getLocation(String id) {
		return locations.get(id);
	}

	public void setLocation(String id, Vehicle vehicle) {
		if(locations.replace(id, vehicle) == null) {
			throw new IllegalArgumentException("Invalid ID: " + id);
		}
	}

	public void setTargetID(String id) {
		targetID = id;
	}

	public String getTargetID() {
		return targetID;
	}
}
