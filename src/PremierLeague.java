import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class PremierLeague {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		ArrayList<String> filePaths = readAllPaths();
		System.out.println(filePaths.size());
				
		PremierLeagueRunner runner = new PremierLeagueRunner(filePaths, 0, filePaths.size());
		Thread thread = new Thread(runner);
		thread.start();		
			
		thread.join();
		System.out.println(Arrays.asList(runner.getReadings()));
		
		ArrayList<String> merged = new ArrayList<String>();
		mergeCounts(runner.getReadings(), merged);

		writeCSV(merged);	
		
	}
  
	private static ArrayList<String> readAllPaths() {
		System.out.println("readAllPaths");
		ArrayList<String> paths = new ArrayList<String>();
		try{
			Files.walk(Paths.get("C:\\Users\\Akshat\\Documents\\EnglishPremierLeagueData\\EPL_Data_1992_2015")).filter(Files::isRegularFile).map(path -> path.toString()).forEach(paths::add);		
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
		return paths;		
	}
	
	private static void mergeCounts (ArrayList<String> runnerCount, ArrayList<String> mergeCount){
		System.out.println("mergeCounts");
		for (String d : runnerCount){
			mergeCount.add(d);
		}
	}
	
	private static void writeCSV (ArrayList<String> combined) throws FileNotFoundException {
		System.out.println("writeCSV");
		File csvFile = new File("C:\\Users\\Akshat\\Documents\\EnglishPremierLeagueData\\PremierLeague.csv");
		csvFile.getParentFile().mkdirs();
		
		PrintWriter writer = new PrintWriter(csvFile);
		writer.write("season,rank,team,played,wins,draws,losses,goals_for,goals_against,goals_difference,points");
		
		for (String d : combined) {
			writer.write("\n" + d);
		}
		writer.close();
		System.out.println("finished");
	}
	
}
