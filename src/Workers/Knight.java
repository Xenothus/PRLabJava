package Workers;

import Workers.Abstract.Worker;
import Workplaces.Army;
import Workplaces.Weaponry;

public class Knight extends Worker {

    private static int ID = 0;
    private final Army army = Army.getInstance();
    private Weaponry weaponry = Weaponry.getInstance();

    private int weaponDemand;

    public Knight()
    {
        myID = ID++;

        waterConsumption = 3;
        foodConsumption = 3;
        productionDuration = 1;
        productionAmount = 10;

        weaponDemand = 1;
    }

    public Knight(int waterConsumption,
                  int foodConsumption,
                  int productionDuration,
                  int productionAmount,
                  int weaponDemand)
    {
        myID = ID++;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.productionDuration = productionDuration;
        this.productionAmount = productionAmount;

        this.weaponDemand = weaponDemand;
    }

    @Override
    public void run()
    {
        reportWorkStarted();

        while (day.isLasting() && work.isOrdered())
        {
            growInStrength();
            if (!satisfyAllNeeds())
                break;
        }

        reportWorkFinished();
    }

    private void growInStrength()
    {
        if (weaponry.takeWeapons(weaponDemand) == 0)
            return;

        work();
        army.putStrength(productionAmount);
    }
}