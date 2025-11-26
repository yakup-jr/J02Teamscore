package ru.teamscore.checkduplicates;

import java.util.LinkedHashSet;
import java.util.Set;

public class CheckDuplicate {
    private final Set<String> values;

    public CheckDuplicate() {
        this.values = new LinkedHashSet<>();
    }

    public void add(String string) {
        if (values.contains(string)) {
            int position = 1;
            for (String str : values) {
                if (str.equals(string)) {
                    break;
                }
                position++;
            }
            throw new AlreadyExistsException(string, position);
        }
        values.add(string);
    }
}
