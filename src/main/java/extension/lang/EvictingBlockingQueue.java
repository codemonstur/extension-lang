package extension.lang;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * The {@code EvictingBlockingQueue} class.
 *
 * @param <T> the type parameter
 */
public final class EvictingBlockingQueue<T> {

    private final int capacity;
    private final ArrayBlockingQueue<T> queue;

    /**
     * Creates a new instance.
     *
     * @param capacity the capacity
     */
    public EvictingBlockingQueue(final int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity+10);
        this.capacity = capacity;
    }

    /**
     * Returns the result of {@code take}.
     *
     * @return the result
     * @throws InterruptedException if the operation fails
     */
    public T take() throws InterruptedException {
        return queue.take();
    }

    /**
     * Performs the {@code offer} operation.
     *
     * @param object the object
     */
    public void offer(final T object) {
        if (queue.size() >= capacity)
            queue.poll();
        queue.offer(object);
    }
}