package Places;

import Main.Queue;

abstract public class SimpleWorkplace {

    protected int productStorage;
    protected Queue productDemandList;
    protected boolean isProductStorageOpen;

    protected void init()
    {
        productStorage = 0;
        productDemandList = new Queue();
        isProductStorageOpen = true;
    }



    protected synchronized int takeProduct(int units)
    {
        try
        {
            if (!isProductStorageOpen)
                return 0;

            if (units > productStorage || !productDemandList.isEmpty())
            {
                productDemandList.push(units);
                wait();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }

        if (!isProductStorageOpen)
            return 0;

        productStorage -= units;
        return units;
    }

    protected synchronized void putProduct(int units)
    {
        productStorage += units;
        if (productStorage >= productDemandList.peek())
        {
            productDemandList.pop();
            notify();
        }
    }

    protected synchronized int getProductStorageValue()
    {
        return productStorage;
    }

    protected synchronized void setProductStorageValue(int units)
    {
        productStorage = units;
    }

    protected synchronized void closeProductStorage()
    {
        isProductStorageOpen = false;
        notifyAll();
    }
}
