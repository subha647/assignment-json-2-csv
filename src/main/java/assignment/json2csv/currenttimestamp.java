package assignment.json2csv;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class currenttimestamp {
	public void show_currenttimestamp() {
		// Print current time
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy-HH-mm-ss");
		String formattedTimestamp = now.format(formatter);
		System.out.println("Formatted Timestamp: " + formattedTimestamp);
	}

}