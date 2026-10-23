# extension-lang

General-purpose utility classes for the JDK. No external dependencies.

See also [extension-util](extension-util/src/docs/README.md), which overlaps for
encoding/IO concerns and hosts `ByteArrayBuilder` (moved there in 1.2.0).

## Packages

### Encoding and decoding

`Encoding` and `Decoding` are enums grouping common codecs:

- URL encode / decode
- Base64 and Base64URL encode / decode
- Hex helpers

```java
String b64 = Encoding.Base64.encode(bytes);
byte[] raw = Decoding.Base64.decode(b64);
```

### Concurrency

- `Concurrent.newBackgroundThread(Supplier<Boolean> loopGuard, Interruptible)` —
  spawn a background thread that runs until the guard returns `false`.
- `Concurrent.tryForTimes(int, CheckedRunnable)` — bounded retry.
- `EvictingBlockingQueue<T>` — bounded queue that drops its oldest element on
  overflow instead of blocking.

### Functional interfaces

- `CheckedProducer<T>` / `CheckedRunnable` — lambda-friendly interfaces that allow
  checked exceptions, with `checkedToRuntime` bridging helpers.
- `MutableValue<T>` — a one-element holder for use in lambdas.

### IO and resources

- `IO.loadResource(path)` / `IO.loadResource(path, charset)` — read a classpath
  resource fully into a `String`.
- `JarResource` / `newJarResource(name)` — handle a jar resource with a default
  `asString()` method.

### Misc

- `Functions.isNullOrEmpty(CharSequence)` — null-or-empty check.
- `Functions` — additional small predicates.

## Dependency

```xml
<dependency>
  <groupId>com.github.codemonstur</groupId>
  <artifactId>extension-lang</artifactId>
  <version>1.2.0</version>
</dependency>
```

## Building

```sh
make build
```

## License

MIT
