package Places;

abstract public class SimpleWorkplace {

    protected int productStorage = 0;
    protected int lastProductDemand = 0;

    private static final Object lastProductDemandLock = new Object();

    protected void init()
    {
        productStorage = 0;
        lastProductDemand = 0;
    }

    public synchronized int takeProduct(int units)
    {
        try
        {
            if (units > productStorage)
            {
                setLastProductDemand(units);
                wait();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }

        productStorage -= units;
        return units;
    }

    public synchronized void putProduct(int units)
    {
        productStorage += units;
        if (productStorage >= getLastProductDemand())
            notify();
    }

    private int getLastProductDemand()
    {
        synchronized (lastProductDemandLock)
        {
            return lastProductDemand;
        }
    }

    private void setLastProductDemand(int input)
    {
        synchronized (lastProductDemandLock)
        {
            lastProductDemand = input;
        }
    }
}
