package Places;

abstract public class SimpleWorkplace {

    protected int productStorage = 0;
    protected int lastProductDemand = 0;

    protected final Object lastProductDemandLock = new Object();

    protected void init()
    {
        productStorage = 0;
        lastProductDemand = 0;
    }

    protected synchronized int takeProduct(int units)
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

        System.out.println(productStorage);
        productStorage -= units;
        return units;
    }

    protected synchronized void putProduct(int units)
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
