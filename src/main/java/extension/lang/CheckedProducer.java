package extension.lang;

/**
 * The {@code CheckedProducer} interface.
 *
 * @param <T> the type parameter
 */
public interface CheckedProducer<T> {
    /**
     * Returns the result of {@code get}.
     *
     * @return the result
     * @throws Exception if the operation fails
     */
    T get() throws Exception;

    /**
     * Returns the result of {@code checkedToRuntime}.
     *
     * @param <T> the type parameter
     * @param producer the producer
     * @return the result
     */
    public static <T> T checkedToRuntime(final CheckedProducer<T> producer) {
        try {
            return producer.get();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
