package common;

public abstract class Interval implements Runnable {
    private final int milliseconds;

    protected Interval(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    @Override
    public void run() {
        while (canSleep()) {
            execute();
        }
    }

    protected abstract void execute();

    private boolean canSleep() {
        try {
            Thread.sleep(milliseconds);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
