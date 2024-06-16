package extension.lang;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HexFormat;

import static java.nio.charset.StandardCharsets.UTF_8;

public enum Encoding {;

    public static String encodeUrl(final String value) {
        return value != null ? URLEncoder.encode(value, UTF_8) : "";
    }

    public static String encodeBase64(final String value, final Charset charset) {
        return encodeBase64(value.getBytes(charset));
    }

    public static String encodeBase64(final byte[] value) {
        return Base64.getEncoder().encodeToString(value);
    }

    public static String encodeBase64Url(final byte[] value) {
        return Base64.getUrlEncoder().encodeToString(value);
    }

    public static String encodeHex(final byte[] bytes) {
        return HexFormat.of().formatHex(bytes);
    }

}
