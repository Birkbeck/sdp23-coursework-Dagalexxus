package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import sml.exceptions.DuplicateLabelException;

// TODO: write a JavaDoc for the class

/**
 * Responsible for holding the labels of instructions for quick lookup of the related address in the program.
 * Required in case of a jump instruction.
 *
 * @author Niklas Hassforther
 * @version 1.0
 * @since 1.0
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 * @throws DuplicateLabelException in case the label is already contained.
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.
		if (this.labels.containsKey(label)){
			throw new DuplicateLabelException("Label " + label + " already in use.");
		}
		else {
			labels.put(label, address);
		}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 * @throws NullPointerException in case the label does not exist.
	 */
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
		// If there is a non-existent label the method get() from the HashMap will return a null value.
		// This means that the method returns a null value when it expects an int.
		// The NullPointerException should be thrown at that moment.

		if (labels.containsKey(label)) {
			return labels.get(label);
		}
		else{
			throw new NullPointerException("Label" + label + "does not exist in the program.");
		}
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		return this.labels.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.map(e -> e.getKey() + " -> " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]"));
	}

	// TODO: Implement equals and hashCode (needed in class Machine).
	@Override
	public boolean equals(Object o) {
		if (o instanceof Labels other) {
			return labels.equals(other.labels);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return labels.hashCode();
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
