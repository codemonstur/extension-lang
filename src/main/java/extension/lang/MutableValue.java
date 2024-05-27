package extension.lang;

// Just a storage container for a single value, can be used if a lambda needs to return a value
public final class MutableValue<T> {
    private T value;
    public MutableValue() {}
    public MutableValue(final T value) {
        this.value = value;
    }
    public void set(final T value) {
        this.value = value;
    }
    public T get() {
        return value;
    }
}
