package Places;

import Main.Queue;

abstract public class ComplexWorkplace {

    protected int product1Storage;
    protected int product2Storage;
    protected Queue product1Demand;
    protected Queue product2Demand;
    protected boolean isProduct1StorageOpen;
    protected boolean isProduct2StorageOpen;

    protected final Object product1StorageLock = new Object();
    protected final Object product2StorageLock = new Object();
    protected final Object product1DemandLock = new Object();
    protected final Object product2DemandLock = new Object();

    protected void init()
    {
        product1Storage = 0;
        product2Storage = 0;
        product1Demand = new Queue();
        product2Demand = new Queue();
        isProduct1StorageOpen = true;
        isProduct2StorageOpen = true;
    }

    protected int takeProduct1(int units)
    {
        synchronized (product1StorageLock)
        {
            try
            {
                if (units > product1Storage  && !product1Demand.isEmpty())
                {
                    pushProduct1Demand(units);
                    product1StorageLock.wait();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }

            if (!isProduct1StorageOpen)
                return 0;

            product1Storage -= units;
            return units;
        }
    }

    protected void putProduct1(int units)
    {
        synchronized (product1StorageLock)
        {
            product1Storage += units;
            if (product1Storage >= getProduct1Demand())
            {
                product1Demand.pop();
                product1StorageLock.notify();
            }
        }
    }

    protected int getProduct1StorageValue()
    {
        synchronized (product1StorageLock)
        {
            return product1Storage;
        }
    }

    protected void setProduct1StorageValue(int units)
    {
        synchronized (product1StorageLock)
        {
            product1Storage = units;
        }
    }

    protected void closeProduct1Storage()
    {
        synchronized (product1StorageLock)
        {
            isProduct1StorageOpen = false;
            product1StorageLock.notifyAll();
        }
    }
    
    protected int takeProduct2(int units)
    {
        synchronized (product2StorageLock)
        {
            try
            {
                if (units > product2Storage && !product2Demand.isEmpty())
                {
                    pushProduct2Demand(units);
                    product2StorageLock.wait();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        }

        if (!isProduct2StorageOpen)
            return 0;

        product2Storage -= units;
        return units;
    }

    protected void putProduct2(int units)
    {
        synchronized (product2StorageLock)
        {
            product2Storage += units;
            if (product2Storage >= getProduct2Demand())
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

    protected void setProduct2StorageValue(int units)
    {
        synchronized (product2StorageLock)
        {
            product2Storage = units;
        }
    }

    protected void closeProduct2Storage()
    {
        synchronized (product2StorageLock)
        {
            isProduct2StorageOpen = false;
            product1StorageLock.notifyAll();
        }
    }

    protected int getProduct1Demand()
    {
        synchronized (product1DemandLock)
        {
            return product1Demand.getFirst();
        }
    }

    protected void pushProduct1Demand(int input)
    {
        synchronized (product1DemandLock)
        {
            product1Demand.push(input);
        }
    }

    protected int getProduct2Demand()
    {
        synchronized (product2DemandLock)
        {
            return product2Demand.getFirst();
        }
    }

    protected void pushProduct2Demand(int input)
    {
        synchronized (product2DemandLock)
        {
            product2Demand.push(input);
        }
    }

}
