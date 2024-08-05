package extension.lang;

import java.util.function.Supplier;

public enum Functions {;

    public static boolean isNullOrEmpty(final String value) {
        return value == null || value.isEmpty();
    }

}
