
public abstract class AbstractShelter implements IShelter {
	private final String id;
	private final int volume;
	private final boolean available;
	private final int land;
	private final ShelterType type;
	public AbstractShelter(String id, int volume, boolean available, int land, ShelterType type) {
		this.id = id;
		this.volume = volume;
		this.available = available;
		this.land = land;
		this.type = type;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getId() {
		return id;
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
	public final boolean isAvailable() {
		return available;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getLand() {
		return land;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getWaterType() {
		return type.getWaterType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getClimate() {
		return type.getClimate();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ShelterType getType() {
		return type;
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
		
		// Check if it is a shelter
		if (!(obj instanceof IShelter)) {
			return false;
		}
		IShelter other = (IShelter) obj;
		
		// Compare shelter types
		if (type != other.getType()) {
			return false;
		}
		
		// Compare volumes
		if (volume != other.getVolume()) {
			return false;
		}
		
		// Compare availabilities
		if (available != other.isAvailable()) {
			return false;
		}
	
		// Compare land
		if (land != other.getLand()) {
			return false;
		}

		// Compare IDs
		return id == null ? other.getId() == null : id.equals(other.getId());
	}
	
	/**
	 * Returns user friendly string representation of this object.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(type.name());
		builder.append(" - ");
		builder.append("ID: ").append(id);
		builder.append(" - ");
		builder.append("Volume: ").append(volume).append(" m3");
		builder.append(" - ");
		builder.append("Available: ").append(available);
		builder.append(" - ");
		builder.append("Land surface: ");
		if (land == -1) {
			builder.append("N/A");
		}
		else {
			builder.append(land).append(" m2");
		}
		return builder.toString();
	}
}