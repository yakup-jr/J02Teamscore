package ru.teamscore.checkduplicates;

/**
 * The type Already exists exception.
 */
public class AlreadyExistsException extends RuntimeException {
    private final String value;
    private final int position;

    /**
     * Instantiates a new Already exists exception.
     *
     * @param value    the value
     * @param position the position
     */
    public AlreadyExistsException(String value, int position) {
        super(String.format("Repeatable input: %s. First input time: %d", value, position));
        this.value = value;
        this.position = position;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }
}
