import java.util.Collection;

public interface IGroup<E> {
	/**
	 * Add an element to the group.
	 * @param element Element to add.
	 * @throws IllegalArgumentException if the element is null.
	 * @throws IllegalStateException if the collection already contains this element.
	 */
	public void add(E element);
	
	/**
	 * Returns a modifyable collection of the internal collection.
	 * @return a collection.
	 */
	public Collection<E> getCollection();
	
	/**
	 * Get an element by identifier.
	 * @param id Identifier.
	 * @return the element with the specified identifier or null if no such element is in the internal collection.
	 * @throws IllegalArgumentException if the identifier is null
	 */
	public E getById(String id);
}
