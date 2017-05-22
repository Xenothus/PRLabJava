package Places;

public class Well {

    private static int waterStorage = 0;
    private static int lastWaterDemand = 0;

    private static final Object lastWaterDemandLock = new Object();
    private static class SingletonHelper {
        private static final Well instance = new Well();
    }

    private Well(){}

    public static Well getInstance()
    {
        return SingletonHelper.instance;
    }

    public synchronized int takeWater(int tons)
    {
        try
        {
            if (tons > waterStorage)
            {
                setLastWaterDemand(tons);
                wait();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }

        waterStorage -= tons;
        return tons;
    }

    public synchronized void putWater(int tons)
    {
        waterStorage += tons;
        if (waterStorage >= getLastWaterDemand())
            notify();
    }

    private int getLastWaterDemand()
    {
        synchronized (lastWaterDemandLock)
        {
            return lastWaterDemand;
        }
    }

    private void setLastWaterDemand(int input)
    {
        synchronized (lastWaterDemandLock)
        {
            Well.lastWaterDemand = input;
        }
    }
}
