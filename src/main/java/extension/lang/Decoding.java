package extension.lang;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;

import static java.lang.Character.digit;
import static java.nio.charset.StandardCharsets.UTF_8;

public enum Decoding {;

    public static String decodeUrl(final String value) {
        return value != null ? URLDecoder.decode(value, UTF_8) : "";
    }

    public static String decodeBase64(final String value, final Charset charset) {
        return new String(decodeBase64(value), charset);
    }
    public static byte[] decodeBase64(final String value) {
        return Base64.getDecoder().decode(value);
    }

    public static byte[] decodeBase64Url(final String text) {
        return Base64.getUrlDecoder().decode(text);
    }

    public static byte[] decodeHex(final String hex) {
        return decodeHex(hex.toCharArray());
    }
    public static byte[] decodeHex(final char[] hex) {
        final var data = new byte[hex.length/2];
        for (int i = 0; i < hex.length; i += 2) {
            data[i/2] = (byte) ((digit(hex[i], 16) << 4)
                    + digit(hex[i + 1], 16));
        }
        return data;
    }

}
