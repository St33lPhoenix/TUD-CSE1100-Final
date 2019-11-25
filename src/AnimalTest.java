import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
	private static final IAnimal A1 = new Animal("Penguin", 300, Arrays.asList(ShelterType.TUNDRA, ShelterType.COASTAL));
	private static final IAnimal A2 = new Animal("Penguin", 300, Arrays.asList(ShelterType.TUNDRA, ShelterType.COASTAL));
	private static final IAnimal A3 = new Animal("NotAPenguin", 300, Arrays.asList(ShelterType.TUNDRA, ShelterType.COASTAL));
	@Test
	public void testGetName() {
		assertEquals(A1.getName(), "Penguin");
	}
	@Test
	public void testGetVolume() {
		assertEquals(A2.getVolume(), 300);
	}
	@Test
	public void testGetShelters() {
		assertEquals(A3.getPreferredShelters(), Arrays.asList(ShelterType.TUNDRA, ShelterType.COASTAL));
	}
	@Test
	public void testEqualsSame() {
		assertEquals(A1, A1);
	}
	@Test
	public void testEqualsEqual() {
		assertEquals(A1, A2);
	}
	@Test
	public void testEqualsNotEqual() {
		assertNotEquals(A1, A3);
	}
	@Test
	public void testEqualsTypeDifference() {
		assertNotEquals(A2, new Object());
	}
	@Test
	public void testToString() {
		assertEquals(A1.toString(), "Penguin - Requires: 300 m3 - Preferred shelter: tundra, coastal");
	}
}
