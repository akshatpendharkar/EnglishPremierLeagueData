// Akshat Pendharkar
// PremierLeagueRunner.java

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class PremierLeagueRunner implements Runnable{
	private ArrayList<String> filePaths;
	private int start;
	private int end;
	private ArrayList<String> readings;
	
	public ArrayList<String> getReadings()
	{
		return readings;
	}

	public PremierLeagueRunner(ArrayList<String> filePaths, int start, int end) {
		this.filePaths = filePaths;
		this.start = start;
		this.end = end;
	}
	
	public void run() {
		this.readings = new ArrayList<String>();

		for (int i = start; i < end; i++) {
			FileReader jsonFile = null;
			
			try {
				jsonFile = new FileReader(filePaths.get(i));
			}
			catch (FileNotFoundException exp) {
				exp.printStackTrace();
			}
			
			JsonParser jparser = new JsonParser();
			JsonArray jarray = jparser.parse(jsonFile).getAsJsonArray();
			
			for (int j = 0; j < jarray.size(); j++) {
				JsonObject jobject = (JsonObject) jarray.get(j);
				String yearStart = String.valueOf(1992 + i);	
				String yearEnd = String.valueOf(1992 + i + 1);	
				String season = yearStart + "-" + yearEnd;
				String rank = jobject.get("rank").getAsString();
				String team = jobject.get("team").getAsString();
				String played = jobject.get("played").getAsString();
				String wins = jobject.get("wins").getAsString();
				String draws = jobject.get("draws").getAsString();
				String losses = jobject.get("losses").getAsString();
				String goals_for = jobject.get("goals-for").getAsString();
				String goals_against = jobject.get("goals-against").getAsString();
				String goals_difference = jobject.get("goals-dff").getAsString();
				String points = jobject.get("points").getAsString();
				
				String data = season + "," + rank + "," + team + "," + 
						played + "," + wins + "," + draws + "," + losses + "," + 
						goals_for + "," + goals_against + "," + goals_difference 
						+ "," + points;
				
				readings.add(data);
				
			}	
		}
	}
		
}
