import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Animals extends AbstractGroup<IAnimal> {
	/**
	 * Get a animal by name (case insensitive).
	 * @param name Name to look for.
	 * @return the animal with the specified name or null if no such animal is in the internal collection.
	 * @throws IllegalArgumentException if the name is null
	 */
	@Override
	public final IAnimal getById(String name) {
		// Preconditions
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		
		// Filtering the animals by name
		return coll.stream().filter(animal -> animal.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	/**
	 * Create a new collection of animals from file.
	 * @param file File to use.
	 * @return a animals object.
	 * @throws IOException if an I/O exception occurred.
	 * @throws IllegalArgumentException if the file is null.
	 * @throws IllegalStateException if the file does not exist or if the file is not a file.
	 */
	public static final Animals fromFile(File file) throws IOException {
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
		
		// New Animals instance
		Animals animals = new Animals();
		
		// Try with resources to close the reader afterwards
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			// Trying to create an animal instance as many times as possible and add it to the collection
			IAnimal animal;
			while ((animal = fromReader(reader)) != null) {
				animals.add(animal);
			}
		}
		
		// Return the Animals instance
		return animals;
	}
	
	/**
	 * Create a animal from a reader.
	 * We assume the input is parseable and the animal has at least one possible shelter.
	 * @param reader Reader to use.
	 * @return a animal or null if the end of the stream was reached. 
	 * @throws IOException if an I/O exception occurred.
	 * @throws IllegalArgumentException if the reader is null.
	 */
	private static final IAnimal fromReader(BufferedReader reader) throws IOException {
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
		
		// Format: <name>; <volume>; <shelter1>, <shelter2>, ..., <shelterN>
		String[] split = line.split("[;,] "); // ; or , followed by a space
		
		String name = split[0]; // name
		
		int volume = Integer.parseInt(split[1]); // volume
		
		List<ShelterType> preferred = Arrays.stream(split, 2, split.length) // Stream of all arguments starting from index 2
				.map(type -> ShelterType.match(type)) // To sheltertype
				.filter(type -> type != null) // Filter out unknown types
				.collect(Collectors.toList()); // List of preferred shelters
		
		return new Animal(name, volume, preferred);
	}
}
