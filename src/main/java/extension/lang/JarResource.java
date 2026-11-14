package extension.lang;

import java.io.FileNotFoundException;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * The {@code JarResource} interface.
 */
public interface JarResource {
    /**
     * Returns the resource path.
     *
     * @return the result
     */
    String getResourcePath();

    /**
     * Returns the result of {@code newJarResource}.
     *
     * @param name the name
     * @return the result
     */
    static JarResource newJarResource(final String name) {
        return () -> name;
    }

    /**
     * Returns the result of {@code asString}.
     *
     * @return the result
     * @throws IOException if the operation fails
     */
    default String asString() throws IOException {
        try (final var in = JarResource.class.getResourceAsStream(getResourcePath())) {
            if (in == null) throw new FileNotFoundException("Resource " + getResourcePath() + " does not exist.");
            return new String(in.readAllBytes(), UTF_8);
        }
    }

}
