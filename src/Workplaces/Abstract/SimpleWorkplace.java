package Workplaces.Abstract;

import Auxiliary.Queue;

abstract public class SimpleWorkplace {

    protected String productName;
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
        reportStorageState('O', units);
        return units;
    }

    protected synchronized void putProduct(int units)
    {
        productStorage += units;
        reportStorageState('I', units);
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

    private void reportStorageState(char IO, int units)
    {
        StringBuilder sb = new StringBuilder();

        String name = getClass().getSimpleName();
        sb.append(name);
        sb.append("\t");
        if (name.length() < 8)
            sb.append("\t");

        sb.append(productName);
        sb.append("\t");
        if (productName.length() < 8)
            sb.append("\t");

        sb.append(IO == 'I' ? "IN: " : "OUT: ");
        sb.append(units);
        sb.append("\t\t");

        sb.append("CURRENT: ");
        sb.append(productStorage);

        System.out.println(sb.toString());
    }
}
