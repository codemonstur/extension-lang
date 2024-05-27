package extension.lang;

import java.util.concurrent.ArrayBlockingQueue;

public final class EvictingBlockingQueue<T> {

    private final int capacity;
    private final ArrayBlockingQueue<T> queue;

    public EvictingBlockingQueue(final int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity+10);
        this.capacity = capacity;
    }

    public T take() throws InterruptedException {
        return queue.take();
    }

    public void offer(final T object) {
        if (queue.size() >= capacity)
            queue.poll();
        queue.offer(object);
    }
}