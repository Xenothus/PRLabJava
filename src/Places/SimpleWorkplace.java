package Places;

import Main.Queue;

abstract public class SimpleWorkplace {

    protected int productStorage;
    protected Queue productDemand;

    protected final Object productDemandLock = new Object();

    protected void init()
    {
        productStorage = 0;
        productDemand = new Queue();
    }

    protected synchronized int takeProduct(int units)
    {
        try
        {
            if (units > productStorage)
            {
                pushProductDemand(units);
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

    protected synchronized void putProduct(int units)
    {
        productStorage += units;
        if (productStorage >= getProductDemand())
        {
            productDemand.pop();
            notify();
        }
    }

    protected synchronized int getProductStorageValue()
    {
        return productStorage;
    }

    private int getProductDemand()
    {
        synchronized (productDemandLock)
        {
            return productDemand.getFirst();
        }
    }

    private void pushProductDemand(int input)
    {
        synchronized (productDemandLock)
        {
            productDemand.push(input);
        }
    }
}
