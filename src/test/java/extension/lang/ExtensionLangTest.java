package extension.lang;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExtensionLangTest {

    @Test
    void functions_isNullOrEmpty() {
        assertTrue(Functions.isNullOrEmpty(null));
        assertTrue(Functions.isNullOrEmpty(""));
        assertFalse(Functions.isNullOrEmpty("x"));
    }

    @Test
    void encoding_roundTrip() {
        assertEquals("a+b", Encoding.encodeUrl("a b"));
        assertEquals("a b", Decoding.decodeUrl("a+b"));
        final byte[] data = "hello".getBytes(UTF_8);
        final String b64 = Encoding.encodeBase64(data);
        assertArrayEquals(data, Decoding.decodeBase64(b64));
        assertEquals("hello", Decoding.decodeBase64(b64, UTF_8));
        assertEquals(b64, Encoding.encodeBase64("hello", UTF_8));
        final String urlB64 = Encoding.encodeBase64Url(data);
        assertArrayEquals(data, Decoding.decodeBase64Url(urlB64));
        final String hex = Encoding.encodeHex(data);
        assertArrayEquals(data, Decoding.decodeHex(hex));
        assertArrayEquals(data, Decoding.decodeHex(hex.toCharArray()));
        // null handling
        assertEquals("", Encoding.encodeUrl(null));
        assertEquals("", Decoding.decodeUrl(null));
    }

    @Test
    void decoding_decodeBase64_charset() {
        assertEquals("hi", Decoding.decodeBase64(Encoding.encodeBase64("hi".getBytes(UTF_8)), UTF_8));
    }

    @Test
    void mutableValue() {
        final MutableValue<String> mv = new MutableValue<>();
        mv.set("a");
        assertEquals("a", mv.get());
        assertEquals("b", new MutableValue<>("b").get());
    }

    @Test
    void checkedProducer_checkedToRuntime() {
        assertEquals(42, CheckedProducer.checkedToRuntime(() -> 42));
        assertThrows(RuntimeException.class, () -> CheckedProducer.checkedToRuntime(() -> { throw new Exception("boom"); }));
    }

    @Test
    void concurrent_tryForTimes_success() throws Exception {
        final AtomicInteger counter = new AtomicInteger();
        Concurrent.tryForTimes(3, () -> { if (counter.incrementAndGet() < 2) throw new Exception("fail"); });
        assertEquals(2, counter.get());
    }

    @Test
    void concurrent_tryForTimes_allFail() {
        assertThrows(Exception.class, () -> Concurrent.tryForTimes(2, () -> { throw new Exception("boom"); }));
    }

    @Test
    void concurrent_tryWithReturn() throws Exception {
        assertEquals("ok", Concurrent.tryWithReturn(3, (Callable<String>) () -> "ok"));
    }

    @Test
    void evictingBlockingQueue_offerEvicts() throws InterruptedException {
        final EvictingBlockingQueue<Integer> q = new EvictingBlockingQueue<>(2);
        q.offer(1);
        q.offer(2);
        q.offer(3);
        // capacity 2: first evicted, so first taken should be 2
        assertEquals(2, q.take().intValue());
        assertEquals(3, q.take().intValue());
    }

    @Test
    void io_loadResource_missingThrows() {
        assertThrows(FileNotFoundException.class, () -> IO.loadResource("/no/such/resource.txt"));
    }

    @Test
    void io_loadResource_bytesMissingThrows() {
        assertThrows(FileNotFoundException.class, () -> IO.loadResource("/no/such/resource.txt"));
    }

    @Test
    void jarResource_missingThrows() {
        final JarResource r = JarResource.newJarResource("/no/such/resource.txt");
        assertEquals("/no/such/resource.txt", r.getResourcePath());
        assertThrows(FileNotFoundException.class, r::asString);
    }

}
