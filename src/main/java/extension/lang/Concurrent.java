package extension.lang;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public enum Concurrent {;

    public interface Interruptible {
        void run() throws InterruptedException;
    }

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
