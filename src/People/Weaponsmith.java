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
        productionDuration = 1;
        productionAmount = 100;

        ironBarsDemand = 1;
    }

    public Weaponsmith(int waterConsumption,
                       int foodConsumption,
                       int productionDuration,
                       int productionAmount,
                       int ironBarsDemand)
    {
        myID = ID++;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.productionDuration = productionDuration;
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
            if (!satisfyAllNeeds())
                break;
        }

        weaponry.closeWeaponsStorage();

        reportWorkFinished();
    }

    private void forgeWeapons()
    {
       if (ironworks.takeIronBars(ironBarsDemand) == 0)
           return;

        work();
        weaponry.putWeapons(productionAmount);
    }
}
