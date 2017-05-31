package Workers;

import Workers.Abstract.Worker;
import Workplaces.Weaponry;
import Workplaces.Ironworks;

public class Weaponsmith extends Worker implements Runnable {

    private static int ID = 0;
    private Weaponry weaponry = Weaponry.getInstance();
    private Ironworks ironworks = Ironworks.getInstance();

    private int ironBarsDemand;

    public Weaponsmith()
    {
        myID = ID++;

        waterConsumption = 2;
        foodConsumption = 2;
        productionDuration = 1;
        productionAmount = 5;

        ironBarsDemand = 5;
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
