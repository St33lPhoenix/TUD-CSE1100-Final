import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Shelters extends AbstractGroup<IShelter> {
	/**
	 * Get a shelter by ID (case sensitive).
	 * @param id ID to look for.
	 * @return the shelter with the specified ID or null if no such shelter is in the internal collection.
	 * @throws IllegalArgumentException if the ID is null
	 */
	public final IShelter getById(String id) {
		// Preconditions
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be null");
		}
		return coll.stream().filter(shelter -> shelter.getId().equals(id)).findFirst().orElse(null);
	}
	
	/**
	 * Create a new collection of shelters from file.
	 * @param file File to use.
	 * @return a Shelters object.
	 * @throws IOException if an I/O exception occurred.
	 * @throws IllegalArgumentException if the file is null.
	 * @throws IllegalStateException if the file does not exist or if the file is not a file.
	 */
	public static final Shelters fromFile(File file) throws IOException {
		// Preconditions
		if (file == null) {
			throw new IllegalArgumentException("File cannot be null");
		}
		if (!file.exists()) {
			throw new IllegalStateException("File does not exist");
		}
		if (!file.isFile()) {
			throw new IllegalStateException("File is not a file");
		}
		
		// New Shelters instance
		Shelters shelters = new Shelters();
		
		// Try with resources to close the reader afterwards
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			// Trying to create a shelter instance as many times as possible and add it to the collection
			IShelter shelter;
			while ((shelter = fromReader(reader)) != null) {
				shelters.add(shelter);
			}
		}
		
		// Return the Shelters instance
		return shelters;
	}
	
	/**
	 * Create a shelter from a reader.
	 * We assume the input is parseable.
	 * @param reader Reader to use.
	 * @return a shelter or null if the end of the stream was reached. 
	 * @throws IOException if an I/O exception occurred.
	 * @throws IllegalArgumentException if the reader is null.
	 */
	private static final IShelter fromReader(BufferedReader reader) throws IOException {
		// Preconditions
		if (reader == null) {
			throw new IllegalArgumentException("Reader cannot be null");
		}

		// Reading the next line
		String line = reader.readLine();
		
		// The end of the stream was reached
		if (line == null) {
			return null;
		}
		
		// Format: <type> <id> <volume> <available> <land>
		String[] split = line.split(" +"); // One or more spaces
		
		String id = split[1]; // id
		
		int volume = Integer.parseInt(split[2]); // volume
		
		boolean available = Boolean.parseBoolean(split[3]); // available
		
		int land = split.length == 5 ? Integer.parseInt(split[4]) : -1; // land (-1 if N/A)
		
		switch (split[0].toLowerCase()) { // type
		// Coastal
		case "coastal":
			return new CoastalShelter(id, volume, available, land);
		// Tundra
		case "tundra":
			return new TundraShelter(id, volume, available, land);
		// Reef
		case "reef":
			return new ReefShelter(id, volume, available, land);
		// Default
		default:
			throw new IllegalArgumentException("Unsupported shelter type: " + split[0]);
		}
	}
}