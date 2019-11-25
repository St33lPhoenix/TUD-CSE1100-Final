import java.util.List;

public interface IAnimal {
	/**
	 * @return the name of the animal.
	 */
	public String getName();
	
	/**
	 * @return the required volume.
	 */
	public int getVolume();
	
	/**
	 * Get a list of possible shelters ordered from most preferred to least preferred.
	 * @return a modifyable copy of the list.
	 */
	public List<ShelterType> getPreferredShelters();
}
