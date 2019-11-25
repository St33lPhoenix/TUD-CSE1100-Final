import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Application extends Thread {
	private final BufferedReader inputReader;
	private final IGroup<IShelter> shelters;
	private final IGroup<IAnimal> animals;
	public Application(BufferedReader reader, IGroup<IShelter> shelters, IGroup<IAnimal> animals) {
		super("Zoo application");
		this.inputReader = reader;
		this.shelters = shelters;
		this.animals = animals;
	}


	/**
	 * Loop through and handle menu options.
	 */
	@Override
	public final void run() {
		// Keep looping until the application terminates.
		outer:
		while (true) {
			
			// Sending options
			printOptions();
			
			// Reading input
			// We read the entire line to ensure any input is accepted without a problem
			String line = read();
			
			// Line is null
			if (line == null) {
				continue;
			}
			
			// Handling the input
			switch (line.toLowerCase()) {
			case  "1":
				option1();
				continue;
			case "2":
				option2();
				continue;
			case "3":
				option3();
				continue;
			case "4":
				option4();
				continue;
			case "5":
				option5();
				continue;
			case "6":
				// Stop the while loop
				break outer;
			default:
				System.out.println("Unknown option: " + line);
			}
		}
	
		// Try closing the resource
		try {
			inputReader.close();
		} catch (IOException exception) {
			System.err.println("Could not properly close input reader: " + exception.getMessage());
		}

		// Exit the application
		System.exit(0);
	}
	
	/**
	 * Safely try to read a line from the BufferedReader.
	 * @return the line read or "" if something went wrong.
	 */
	private final String read() {
		// Reading input
		// We read the entire line to ensure any input is accepted without a problem
		String line = null;
		try {
			line = inputReader.readLine();
		} catch (IOException exception) {
			System.err.println("Could not read line: " + exception.getMessage() + ", falling back on empty String.");
		}
		return line;
	}
	
	/**
	 * Prints all available options.
	 */
	private final void printOptions() {
		System.out.println("Please make your choice:");
		System.out.println("	1 - Show all shelters");
		System.out.println("	2 - Show all animals");
		System.out.println("	3 - Show all shelters suitable for a specific animal");
		System.out.println("	4 - Show the optimal shelters for a specific animal");
		System.out.println("	5 - Show the constant properties per shelter type");
		System.out.println("	6 - Stop the program");
	}
	
	/**
	 * Handles option 1 - show all shelters
	 */
	private final void option1() {
		// Print each shelter to console
		shelters.getCollection().forEach(shelter -> System.out.println(shelter));
	}
	
	/**
	 * Handles option 2 - show all animals
	 */
	private final void option2() {
		// Print each animal to console
		animals.getCollection().forEach(animal -> System.out.println(animal));
	}
	
	/**
	 * Handles option 3 - show all suitable shelters for a specific animal
	 */
	private final void option3() {
		// Print the question
		System.out.println("Animal type:");
		
		// Read the response
		String line = read();
		
		// Get the right shelter type
		IAnimal animal = line == null ? null : animals.getById(line);
		
		// Unknown animal
		if (animal == null) {
			System.out.println("Unknown animal type.");
			return;
		}
		
		List<IShelter> result = shelters.getCollection().stream()
			.filter(shelter -> shelter.getVolume() >= animal.getVolume()) // Shelter is large enough
			.filter(shelter -> animal.getPreferredShelters().contains(shelter.getType())) // The animal prefers this type of shelter
			.collect(Collectors.toList());
		
		// No matching shelter
		if (result.isEmpty()) {
			System.out.println("No matching shelter.");
			return;
		}

		result.forEach(shelter -> System.out.println(shelter)); // Print all available shelters
	}
	
	/**
	 * Handles option 4 - show the optimal shelters for a specific animal
	 */
	private final void option4() {
		// Print the question
		System.out.println("Animal type:");
		
		// Read the response
		String line = read();
		
		// Get the right shelter type
		IAnimal animal = line == null ? null : animals.getById(line);
		
		// Unknown animal
		if (animal == null) {
			System.out.println("Unknown animal type.");
			return;
		}
		
		List<IShelter> result = shelters.getCollection().stream()
			.filter(shelter -> shelter.getVolume() >= animal.getVolume()) // Shelter is large enough
			.filter(shelter -> shelter.isAvailable()) // Shelter is available
			.filter(shelter -> animal.getPreferredShelters().contains(shelter.getType())) // The animal prefers this type of shelter
			.sorted((s1, s2) -> {
				// Compare priority in the preferred shelter list
				int priority = Integer.compare(animal.getPreferredShelters().indexOf(s1.getType()), animal.getPreferredShelters().indexOf(s2.getType()));
				
				// If same priority
				if (priority == 0) {
					
					// Compare volumes
					return Integer.compare(s1.getVolume(), s2.getVolume());
				}
				return priority;
			}) // Sort the shelters
			.collect(Collectors.toList());
		
		// No matching shelter
		if (result.isEmpty()) {
			System.out.println("No matching shelter.");
			return;
		}

		result.forEach(shelter -> System.out.println(shelter)); // Print all available shelters
	}
	
	/**
	 * Handles option 5 - show the constant properties for a shelter type.
	 */
	private final void option5() {
		// Print the question
		System.out.println("Shelter type (coastal, tundra, reef):");
		
		// Read the response
		String line = read();
		
		// Get the right shelter type
		ShelterType type = line == null ? null : ShelterType.match(line);
		
		// Print the result
		System.out.println(type == null ? "Unknown shelter type." : type);
	}

}