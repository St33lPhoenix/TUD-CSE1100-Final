
public enum ShelterType {
	COASTAL ("Cool Eutrophic", "Temperate"),
	REEF ("Cool Eutrophic", "Polar"),
	TUNDRA ("Warp Trophic", "Tropical");
	
	private final String water;
	private final String climate;
	private ShelterType(String water, String climate) {
		this.water = water;
		this.climate = climate;
	}
	
	/**
	 * Get the shelter's water type.
	 * @return the water type.
	 */
	public final String getWaterType() {
		return water;
	}
	
	/**
	 * Get the shelter's climate.
	 * @return the climate.
	 */
	public final String getClimate() {
		return climate;
	}
	
	/**
	 * Returns user friendly string representation of this object.
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Water type: ").append(water);
		builder.append(" - ");
		builder.append("Climate: ").append(climate);
		return builder.toString();
	}
	
	/**
	 * Get the matching shelter type (case insensitive).
	 * @param input Input.
	 * @return the matched shelter or null if no type matches the input.
	 */
	public static final ShelterType match(String input) {
		for (ShelterType type : values()) {
			if (type.name().equalsIgnoreCase(input)) {
				return type;
			}
		}
		return null;
	}
}
