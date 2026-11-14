package extension.lang;

import java.util.function.Supplier;

/**
 * The {@code Functions} enum.
 */
public enum Functions {;

    /**
     * Returns the result of {@code isNullOrEmpty}.
     *
     * @param value the value
     * @return whether the condition holds
     */
    public static boolean isNullOrEmpty(final String value) {
        return value == null || value.isEmpty();
    }

}
