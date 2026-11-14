package extension.lang;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HexFormat;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * The {@code Encoding} enum.
 */
public enum Encoding {;

    /**
     * Returns the result of {@code encodeUrl}.
     *
     * @param value the value
     * @return the result
     */
    public static String encodeUrl(final String value) {
        return value != null ? URLEncoder.encode(value, UTF_8) : "";
    }

    /**
     * Returns the result of {@code encodeBase64}.
     *
     * @param value the value
     * @param charset the charset
     * @return the result
     */
    public static String encodeBase64(final String value, final Charset charset) {
        return encodeBase64(value.getBytes(charset));
    }

    /**
     * Returns the result of {@code encodeBase64}.
     *
     * @param value the value
     * @return the result
     */
    public static String encodeBase64(final byte[] value) {
        return Base64.getEncoder().encodeToString(value);
    }

    /**
     * Returns the result of {@code encodeBase64Url}.
     *
     * @param value the value
     * @return the result
     */
    public static String encodeBase64Url(final byte[] value) {
        return Base64.getUrlEncoder().encodeToString(value);
    }

    /**
     * Returns the result of {@code encodeHex}.
     *
     * @param bytes the bytes
     * @return the result
     */
    public static String encodeHex(final byte[] bytes) {
        return HexFormat.of().formatHex(bytes);
    }

}
