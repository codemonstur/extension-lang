package extension.lang;

public interface CheckedProducer<T> {
    T get() throws Exception;

    public static <T> T checkedToRuntime(final CheckedProducer<T> producer) {
        try {
            return producer.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
