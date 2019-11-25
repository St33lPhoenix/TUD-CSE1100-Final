import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractGroup<E> implements IGroup<E> {
	protected final Collection<E> coll = new ArrayList<>();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void add(E element) {
		if (element == null) {
			throw new IllegalArgumentException("Shelter cannot be null");
		}
		if (coll.contains(element)) {
			throw new IllegalStateException("Shelter is already added");
		}
		coll.add(element);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Collection<E> getCollection() {
		return new ArrayList<>(coll);
	}
}
