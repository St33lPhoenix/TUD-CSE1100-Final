
public interface IShelter {
	/**
	 * @return the id
	 */
	public String getId();

	/**
	 * @return the volume
	 */
	public int getVolume();

	/**
	 * @return the availability
	 */
	public boolean isAvailable();

	/**
	 * @return the land surface area
	 */
	public int getLand();

	/**
	 * @return the water type
	 */
	public String getWaterType();

	/**
	 * @return the climate
	 */
	public String getClimate();
	
	/**
	 * @return the type
	 */
	public ShelterType getType();
}