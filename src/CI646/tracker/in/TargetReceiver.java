package CI646.tracker.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import CI646.tracker.VehicleTracker;

public class TargetReceiver extends Receiver {
	public TargetReceiver(VehicleTracker tracker) {
		super(tracker, null);
	}

	@Override
	public void run() {
		final String message = "Enter an ID [0-9]: ";

		System.out.println(message);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String input = br.readLine();

			while (!input.trim().isEmpty()) {
				tracker.setTargetID("VEHICLE" + input);
				System.out.println(message);
				input = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setTrackedID(String id) {
		this.id = id;
	}
}