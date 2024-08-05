package extension.lang;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public enum IO {;

    public static String loadResource(final String path, final Charset charset) throws IOException {
        return new String(loadResource(path), charset);
    }

    public static byte[] loadResource(final String path) throws IOException {
        try (final var in = IO.class.getResourceAsStream(path)) {
            if (in == null) throw new FileNotFoundException("Resource " + path + " does not exist.");
            return in.readAllBytes();
        }
    }

}
