package assignment.json2csv;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// Show current timestamp
        currenttimestamp current = new currenttimestamp();
        current.show_currenttimestamp();
        
        // Convert json to csv and store it
        // "https://data.sfgov.org/resource/p4e4-a5a7.json";
        String jsonurl = "https://data.sfgov.org/resource/p4e4-a5a7.json";
        jsontocsvconverter j2csv = new jsontocsvconverter();
        String csv_file_name = j2csv.store_json_to_csv(jsonurl);
        
        // Preety print CSV file
        // String csv_file_name = "output.csv"; // Sample csv file
        csvtopreetytable csvprinter = new csvtopreetytable();
        csvprinter.show_table(csv_file_name);
        
        // Search for particular character substring using regx in the csv file
        // Pattern is "roof" and filename is "output.csv"
        String pattern = "roof";
        findrow findandprintrows = new findrow();
        String[] inputs = {csv_file_name,pattern};
        findandprintrows.using_regx(inputs);
        
        
    }
}