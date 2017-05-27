package Workplaces.Abstract;

import Auxiliary.Queue;

abstract public class ComplexWorkplace {

    protected int product1Storage;
    protected int product2Storage;
    protected Queue product1DemandList;
    protected Queue product2DemandList;
    protected boolean isProduct1StorageOpen;
    protected boolean isProduct2StorageOpen;

    protected final Object product1StorageLock = new Object();
    protected final Object product2StorageLock = new Object();

    protected void init()
    {
        product1Storage = 0;
        product2Storage = 0;
        product1DemandList = new Queue();
        product2DemandList = new Queue();
        isProduct1StorageOpen = true;
        isProduct2StorageOpen = true;
    }



    //TAKE METHODS ------------------------------------------------------

    protected int takeProduct1(int units)
    {
        synchronized (product1StorageLock)
        {
            if (!isProduct1StorageOpen)
                return 0;

            try
            {
                if (units > product1Storage || !product1DemandList.isEmpty())
                {
                    product1DemandList.push(units);
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

    protected int takeProduct2(int units)
    {
        synchronized (product2StorageLock)
        {
            if (!isProduct2StorageOpen)
                return 0;

            try
            {
                if (units > product2Storage || !product2DemandList.isEmpty())
                {
                    product2DemandList.push(units);
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



    //PUT METHODS ------------------------------------------------------

    protected void putProduct1(int units)
    {
        synchronized (product1StorageLock)
        {
            product1Storage += units;
            if (product1Storage >= product1DemandList.peek())
            {
                product1DemandList.pop();
                product1StorageLock.notify();
            }
        }
    }

    protected void putProduct2(int units)
    {
        synchronized (product2StorageLock)
        {
            product2Storage += units;
            if (product2Storage >= product2DemandList.peek())
                product2StorageLock.notify();
        }
    }



    //GETTER METHODS ------------------------------------------------------

    protected int getProduct1StorageValue()
    {
        synchronized (product1StorageLock)
        {
            return product1Storage;
        }
    }

    protected int getProduct2StorageValue()
    {
        synchronized (product2StorageLock)
        {
            return product2Storage;
        }
    }



    //SETTER METHODS ------------------------------------------------------

    protected void setProduct1StorageValue(int units)
    {
        synchronized (product1StorageLock)
        {
            product1Storage = units;
        }
    }

    protected void setProduct2StorageValue(int units)
    {
        synchronized (product2StorageLock)
        {
            product2Storage = units;
        }
    }



    //CLOSE STORAGE METHODS ------------------------------------------------------

    protected void closeProduct1Storage()
    {
        synchronized (product1StorageLock)
        {
            isProduct1StorageOpen = false;
            product1StorageLock.notifyAll();
        }
    }

    protected void closeProduct2Storage()
    {
        synchronized (product2StorageLock)
        {
            isProduct2StorageOpen = false;
            product2StorageLock.notifyAll();
        }
    }
}
