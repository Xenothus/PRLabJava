package Main;

public class DayInfo {

    private boolean isLasting;

    private static class SingletonHelper {
        private static final DayInfo instance = new DayInfo();
    }

    private DayInfo()
    {
        isLasting = true;
    }

    public static DayInfo getInstance()
    {
        return SingletonHelper.instance;
    }

    public synchronized boolean isLasting()
    {
        return isLasting;
    }

    synchronized void dayEnded()
    {
        isLasting = false;
    }
}
