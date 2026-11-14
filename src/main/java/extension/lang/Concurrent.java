package extension.lang;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * The {@code Concurrent} enum.
 */
public enum Concurrent {;

    /**
     * The {@code Interruptible} interface.
     */
    public interface Interruptible {
        /**
         * Runs the operation.
         *
         * @throws InterruptedException if the operation fails
         */
        void run() throws InterruptedException;
    }

    /**
     * Returns the result of {@code newBackgroundThread}.
     *
     * @param loopGuard the loop guard
     * @param action the action
     * @return the result
     */
    public static Thread newBackgroundThread(final Supplier<Boolean> loopGuard, final Interruptible action) {
        return new Thread(() -> {
            try {
                while (loopGuard.get()) {
                    action.run();
                }
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
    /**
     * Performs the {@code tryForTimes} operation.
     *
     * @param times the times
     * @param run the run
     * @throws Exception if the operation fails
     */
    public static void tryForTimes(final int times, final CheckedRunnable run) throws Exception {
        int tries = 0;
        Exception e = null;
        while (tries < times) {
            try {
                run.run();
                return;
            } catch (Exception ex) {
                e = ex;
            }
            tries++;
        }
        throw e;
    }

    /**
     * Returns the result of {@code tryWithReturn}.
     *
     * @param <T> the type parameter
     * @param times the times
     * @param call the call
     * @return the result
     * @throws Exception if the operation fails
     */
    public static <T> T tryWithReturn(final int times, final Callable<T> call) throws Exception {
        int tries = 0;
        Exception e = null;
        while (tries < times) {
            try {
                return call.call();
            } catch (final Exception ex) {
                e = ex;
            }
            tries++;
        }
        throw e;
    }

}
