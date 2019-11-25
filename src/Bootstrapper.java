import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bootstrapper {
	/**
	 * Application entry point.
	 * @param args Command line arguments (ignored)
	 * @throws IOException if an I/O error occurred.
	 */
	public static void main(String[] args) throws IOException {
		
		// Reading user input for shelter file name and animal file name
		String shelterFile;
		String animalFile;
		
		// We don't close it because we use it in the application
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Shelter file name:");
		shelterFile = reader.readLine();
		System.out.println("Animal file name:");
		animalFile = reader.readLine();
		
		// Get the parsed shelter and animal data
		Shelters shelters = Shelters.fromFile(new File(shelterFile));
		Animals animals = Animals.fromFile(new File(animalFile));
		
		// Run the application
		new Application(reader, shelters, animals).start();
	}
}
