package assignment.json2csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class findrow {
	public void using_regx(String[] args) {
		
		String filepath = args[0];
		String pattern_str = args[1];
		
		// Find record with regex
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            Pattern pattern = Pattern.compile(pattern_str, Pattern.CASE_INSENSITIVE);

            while ((line = reader.readLine()) != null) {
                // Use regular expression to find the word "roof" (case-insensitive) in each line
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    // If "roof" is found, print the entire line
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
				
	}
}