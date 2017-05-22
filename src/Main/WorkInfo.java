package Main;

public class WorkInfo {

    private boolean isOrdered;

    private static class SingletonHelper {
        private static final WorkInfo instance = new WorkInfo();
    }

    private WorkInfo()
    {
        isOrdered = true;
    }

    public static WorkInfo getInstance()
    {
        return SingletonHelper.instance;
    }

    public synchronized boolean isOrdered()
    {
        return isOrdered;
    }

    synchronized void endWorkOrder()
    {
        isOrdered = false;
    }
}
