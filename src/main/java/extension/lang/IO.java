package extension.lang;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * The {@code IO} enum.
 */
public enum IO {;

    /**
     * Returns the result of {@code loadResource}.
     *
     * @param path the path
     * @param charset the charset
     * @return the result
     * @throws IOException if the operation fails
     */
    public static String loadResource(final String path, final Charset charset) throws IOException {
        return new String(loadResource(path), charset);
    }

    /**
     * Returns the result of {@code loadResource}.
     *
     * @param path the path
     * @return the result
     * @throws IOException if the operation fails
     */
    public static byte[] loadResource(final String path) throws IOException {
        try (final var in = IO.class.getResourceAsStream(path)) {
            if (in == null) throw new FileNotFoundException("Resource " + path + " does not exist.");
            return in.readAllBytes();
        }
    }

}
