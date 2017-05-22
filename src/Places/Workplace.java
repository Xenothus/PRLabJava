package Places;

abstract public class Workplace {

    protected int product1Storage;
    protected int product2Storage;
    protected int lastProduct1Demand;
    protected int lastProduct2Demand;

    protected final Object product1StorageLock = new Object();
    protected final Object product2StorageLock = new Object();
    protected final Object lastProduct1DemandLock = new Object();
    protected final Object lastProduct2DemandLock = new Object();

    protected void init()
    {
        product1Storage = 0;
        product2Storage = 0;
        lastProduct1Demand = 0;
        lastProduct2Demand = 0;
    }

    protected int takeProduct1(int units)
    {
        synchronized (product1StorageLock)
        {
            try
            {
                if (units > product1Storage)
                {
                    setLastProduct1Demand(units);
                    product1StorageLock.wait();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }

            product1Storage -= units;
            return units;
        }
    }

    protected void putProduct1(int units)
    {
        synchronized (product1StorageLock)
        {
            product1Storage += units;
            if (product1Storage >= getLastProduct1Demand())
                product1StorageLock.notify();
        }
    }

    protected int getProduct1StorageValue()
    {
        synchronized (product1StorageLock)
        {
            return product1Storage;
        }
    }

    protected int takeProduct2(int units)
    {
        synchronized (product2StorageLock)
        {
            try
            {
                if (units > product2Storage)
                {
                    setLastProduct2Demand(units);
                    product2StorageLock.wait();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        }

        product2Storage -= units;
        return units;
    }

    protected void putProduct2(int units)
    {
        synchronized (product2StorageLock)
        {
            product2Storage += units;
            if (product2Storage >= getLastProduct2Demand())
                product2StorageLock.notify();
        }
    }

    protected int getProduct2StorageValue()
    {
        synchronized (product2StorageLock)
        {
            return product2Storage;
        }
    }

    protected int getLastProduct1Demand()
    {
        synchronized (lastProduct1DemandLock)
        {
            return lastProduct1Demand;
        }
    }

    protected void setLastProduct1Demand(int input)
    {
        synchronized (lastProduct1DemandLock)
        {
            lastProduct1Demand = input;
        }
    }

    protected int getLastProduct2Demand()
    {
        synchronized (lastProduct2DemandLock)
        {
            return lastProduct2Demand;
        }
    }

    protected void setLastProduct2Demand(int input)
    {
        synchronized (lastProduct2DemandLock)
        {
            lastProduct2Demand = input;
        }
    }

}
