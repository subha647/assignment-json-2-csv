package assignment.json2csv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opencsv.CSVWriter;

public class jsontocsvconverter {

	public static String store_json_to_csv(String jsonUrl) {
		
		String output_csv_name = "output.csv";
		
        try {

            // Create a connection to the URL
            URL url = new URL(jsonUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the JSON data from the URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();
            connection.disconnect();

            // Parse the JSON data
            JSONArray jsonArray = new JSONArray(jsonContent.toString());
            
            
            // Create a CSV file and write the header
            CSVWriter csvWriter = new CSVWriter (new FileWriter(output_csv_name));
            
            String[] unique_keys = getCsvHeader(jsonArray);
            System.out.println(String.join("\t", unique_keys));
            csvWriter.writeNext(unique_keys);
        	// System.out.printf("%-10s", unique_keys[0]);
            
            // Convert JSON to CSV
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                List<String> row_l = new ArrayList<>();
                
                for (String key : unique_keys) {
                	Object value = jsonObject.optString(key,"");
                	row_l.add((String) value);
                }
                String[] row_ls = row_l.toArray(new String[0]);
//                System.out.println(String.join("\t", row_ls));
                csvWriter.writeNext(row_ls);
            }
            
            // Close the CSV writer
            csvWriter.close();

            System.out.println("JSON data converted to CSV successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return output_csv_name;
    }
	

    // Helper method to convert JSON keys to CSV header
    private static String[] getCsvHeader(JSONArray jsonArray) {
    	// Create a Set to store encountered keys
        Set<String> uniqueKeys = new HashSet<>();
        
    	for (int i = 0; i < jsonArray.length(); i++) {
    		JSONObject jsonObject = jsonArray.getJSONObject(i);
    		String[] keys = JSONObject.getNames(jsonObject);
    		for (String key : keys) {
    			uniqueKeys.add(key);
    		}
        }
    	String[] uniqueKeysArr = uniqueKeys.toArray(new String[0]);
        return uniqueKeysArr;
    }

}