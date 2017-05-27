package People;

import Main.DayInfo;
import Main.WorkInfo;
import Places.Farm;
import Places.Well;

import static Main.DayInfo.getInstance;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

abstract public class Person implements Runnable {

    protected int myID;
    protected final DayInfo day = getInstance();
    protected final WorkInfo work = WorkInfo.getInstance();

    protected int waterConsumption;     // in units
    protected int foodConsumption;     // in units

    protected double productionDuration;   // in seconds
    protected int productionAmount;     // in units

    protected boolean satisfyAllNeeds() {
        if (eat() && drink())
            return true;

        return false;
    }

    protected boolean eat() {
        if (Farm.getInstance().takeCereal(foodConsumption) != 0 ||
                Farm.getInstance().takePotatoes(foodConsumption) != 0)
            return true;

        return false;
    }

    protected boolean drink() {
        if (Well.getInstance().takeWater(waterConsumption) != 0)
            return true;

        return false;
    }

    protected void work() {
        try {
            sleep((int) (productionDuration * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void reportWorkStarted() {
        out.println(getClass().getSimpleName() + " " + myID + " started working");
    }

    protected void reportWorkFinished() {
        out.println(getClass().getSimpleName() + " " + myID + " finished work");
    }
}
