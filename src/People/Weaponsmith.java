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
        ironBarsDemand = 1;
        productionSpeed = 1;
        productionAmount = 100;
    }

    public Weaponsmith(int waterConsumption,
                       int foodConsumption,
                       int ironBarsDemand,
                       int productionSpeed,
                       int productionAmount)
    {
        myID = ID++;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.ironBarsDemand = ironBarsDemand;
        this.productionSpeed = productionSpeed;
        this.productionAmount = productionAmount;
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
