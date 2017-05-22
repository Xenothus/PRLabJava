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
        weaponDemand = 1;
        productionSpeed = 1;
        productionAmount = 100;
    }

    public Knight(int waterConsumption,
                  int foodConsumption,
                  int weaponDemand,
                  int productionSpeed,
                  int productionAmount)
    {
        myID = ID++;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.weaponDemand = weaponDemand;
        this.productionSpeed = productionSpeed;
        this.productionAmount = productionAmount;
    }

    @Override
    public void run()
    {
        reportWorkStarted();

        while (day.isLasting() && work.isOrdered())
        {
            growInStrength();
            eat();
            drink();
        }
    }

    private void growInStrength()
    {
        weaponry.takeWeapons(weaponDemand);
        work();
        army.putStrength(productionAmount);
    }
}
