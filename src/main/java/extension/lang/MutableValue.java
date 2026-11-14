package extension.lang;

// Just a storage container for a single value, can be used if a lambda needs to return a value
/**
 * The {@code MutableValue} class.
 *
 * @param <T> the type parameter
 */
public final class MutableValue<T> {
    private T value;
    /**
     * Creates a new instance.
     */
    public MutableValue() {}
    /**
     * Creates a new instance.
     *
     * @param value the value
     */
    public MutableValue(final T value) {
        this.value = value;
    }
    /**
     * Performs the {@code set} operation.
     *
     * @param value the new value
     */
    public void set(final T value) {
        this.value = value;
    }
    /**
     * Returns the result of {@code get}.
     *
     * @return the result
     */
    public T get() {
        return value;
    }
}
