package People;

import Places.Army;
import Places.Weaponry;

public class Knight extends Person {

    private static int ID = 0;
    private final Army army = Army.getInstance();
    private Weaponry weaponry = Weaponry.getInstance();

    private int weaponDemand;

    public Knight()
    {
        myID = ID++;

        waterConsumption = 1;
        foodConsumption = 1;
        productionSpeed = 1;
        productionAmount = 100;

        weaponDemand = 1;
    }

    public Knight(int waterConsumption,
                  int foodConsumption,
                  int productionSpeed,
                  int productionAmount,
                  int weaponDemand)
    {
        myID = ID++;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.productionSpeed = productionSpeed;
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
    }

    private void growInStrength()
    {
        if (weaponry.takeWeapons(weaponDemand) == 0)
            return;

        work();
        army.putStrength(productionAmount);
    }
}
