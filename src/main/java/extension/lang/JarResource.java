package extension.lang;

import java.io.FileNotFoundException;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public interface JarResource {
    String getResourcePath();

    static JarResource newJarResource(final String name) {
        return () -> name;
    }

    default String asString() throws IOException {
        try (final var in = JarResource.class.getResourceAsStream(getResourcePath())) {
            if (in == null) throw new FileNotFoundException("Resource " + getResourcePath() + " does not exist.");
            return new String(in.readAllBytes(), UTF_8);
        }
    }

}
