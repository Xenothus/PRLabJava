package People;

import Places.Weaponry;
import Places.Ironworks;

public class Weaponsmith extends Person implements Runnable {

    private static int ID = 0;
    private Weaponry weaponry = Weaponry.getInstance();
    private Ironworks ironworks = Ironworks.getInstance();

    private int ironBarsDemand;

    public Weaponsmith()
    {
        myID = ID++;

        waterConsumption = 1;
        foodConsumption = 1;
        productionSpeed = 1;
        productionAmount = 100;

        ironBarsDemand = 1;
    }

    public Weaponsmith(int waterConsumption,
                       int foodConsumption,
                       int productionSpeed,
                       int productionAmount,
                       int ironBarsDemand)
    {
        myID = ID++;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.productionSpeed = productionSpeed;
        this.productionAmount = productionAmount;

        this.ironBarsDemand = ironBarsDemand;
    }

    @Override
    public void run()
    {
        reportWorkStarted();

        while (day.isLasting() && work.isOrdered())
        {
            forgeWeapons();
            eat();
            drink();
        }
    }

    private void forgeWeapons()
    {
        ironworks.takeIronBars(ironBarsDemand);
        work();
        weaponry.putWeapons(productionAmount);
    }
}
