package People;

import Main.DayInfo;
import Main.WorkInfo;
import Places.Farm;
import Places.Well;

abstract public class Person implements Runnable {

    protected int myID;
    protected final DayInfo day = DayInfo.getInstance();
    protected final WorkInfo work = WorkInfo.getInstance();

    protected int waterConsumption;     // in units
    protected int foodConsumption ;     // in units

    protected double productionSpeed;   // in seconds
    protected int productionAmount;     // in units

    protected boolean satisfyAllNeeds()
    {
        if (eat() && drink())
            return true;

        return false;
    }

    protected boolean eat()
    {
        if (Farm.getInstance().takeCereal(foodConsumption) != 0 ||
            Farm.getInstance().takePotatoes(foodConsumption) != 0)
            return true;

        return false;
    }

    protected boolean drink()
    {
        if (Well.getInstance().takeWater(waterConsumption) != 0)
            return true;

        return false;
    }

    protected void work()
    {
        try
        {
            Thread.sleep((int)(productionSpeed * 1000));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    protected void reportWorkStarted()
    {
        System.out.println(getClass().getSimpleName() + " " + myID + " started working");
    }

    protected void reportWorkFinished()
    {
        System.out.println(getClass().getSimpleName() + " " + myID + " finished work");
    }
}
