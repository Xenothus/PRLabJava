package People;

import Places.Mine;

public class Miner extends Person {

    private static int ID = 0;
    private final Mine mine = Mine.getInstance();

    public Miner()
    {
        myID = ID++;

        waterConsumption = 2;
        foodConsumption = 2;
        productionSpeed = 1;
        productionAmount = 100;
    }

    public Miner(int waterConsumption,
                 int foodConsumption,
                 int productionSpeed,
                 int productionAmount)
    {
        myID = ID++;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.productionSpeed = productionSpeed;
        this.productionAmount = productionAmount;
    }

    @Override
    public void run()
    {
        reportWorkStarted();

        while (day.isLasting() && work.isOrdered())
        {
            collectCoal();
            eat();
            drink();

            collectIronOre();
            eat();
            drink();
        }
    }

    private void collectCoal()
    {
        work();
        mine.putCoal(productionAmount);
    }

    private void collectIronOre()
    {
        work();
        mine.putIronOre(productionAmount);
    }
}
