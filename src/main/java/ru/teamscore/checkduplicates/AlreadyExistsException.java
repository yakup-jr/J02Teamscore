package ru.teamscore.checkduplicates;

public class AlreadyExistsException extends RuntimeException {
    private final String value;
    private final int position;

    public AlreadyExistsException(String value, int position) {
        super(String.format("Repeatable input: %s. First input time: %d", value, position));
        this.value = value;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }
}
