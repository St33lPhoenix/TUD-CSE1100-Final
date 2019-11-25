import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Animal implements IAnimal {
	private final String name;
	private final int volume;
	private final List<ShelterType> shelters;
	public Animal(String name, int volume, List<ShelterType> shelters) {
		this.name = name;
		this.volume = volume;
		this.shelters = shelters;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getVolume() {
		return volume;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<ShelterType> getPreferredShelters() {
		return new ArrayList<>(shelters);
	}

	/**
	 * Checks if the object is equal to this instance.
	 */
	@Override
	public boolean equals(Object obj) {
		// Return true if same instance
		if (this == obj) {
			return true;
		}
		
		// Check if it is an animal
		if (!(obj instanceof IAnimal)) {
			return false;
		}
		IAnimal other = (IAnimal) obj;
		
		// Compare volumes
		if (volume != other.getVolume()) {
			return false;
		}
		
		// Compare shelters
		if (!shelters.equals(other.getPreferredShelters())) {
			return false;
		}
		
		// Compare names
		return name == null ? other.getName() == null : name.equals(other.getName());
	}
	
	/**
	 * Returns user friendly string representation of this object.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(" - ");
		builder.append("Requires: ").append(volume).append(" m3");
		builder.append(" - ");
		builder.append("Preferred shelter: ").append(String.join(", ", shelters.stream().map(type -> type.name().toLowerCase()).collect(Collectors.toList())));
		return builder.toString();
	}
}
