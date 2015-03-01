package change.me.util.library;

import java.util.ArrayList;
import java.util.List;

public final class TimeLogger {

    private String label;
    private List<Long> intervals = new ArrayList<>();
    private long start;

    public TimeLogger() {
        this("");
    }

    public TimeLogger(String label) {
        this.label = label;
    }

    public void start() {
        start = System.nanoTime();
    }

    public void finish(boolean needOneShotResult) {
        intervals.add(System.nanoTime() - start);

        if (needOneShotResult) {
            long timeNs = System.nanoTime() - start;
            L.w(label + " shot time = " + timeNs + " ns, or " + (float) timeNs / 1000000 + " ms");
        }
    }

    public void result() {
        long timeNs = 0;
        for (Long interval : intervals) {
            timeNs += interval;
        }
        timeNs = timeNs / intervals.size();

        L.e(label + " time = " + timeNs + " ns, or " + (float) timeNs / 1000000 + " ms");
    }

    public void reset() {
        intervals.clear();
    }

}
