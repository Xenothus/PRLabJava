package People;

import Places.Ironworks;
import Places.Mine;

public class IronWorker extends Person {

    private static int ID = 0;
    private final Ironworks ironworks = Ironworks.getInstance();
    private final Mine mine = Mine.getInstance();

    private int coalDemand;
    private int ironOreDemand;

    public IronWorker()
    {
        myID = ID++;

        waterConsumption = 1;
        foodConsumption = 1;
        coalDemand = 1;
        ironOreDemand = 1;
        productionSpeed = 1;
        productionAmount = 100;
    }

    public IronWorker(int waterConsumption,
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
            forgeIronBars();
            eat();
            drink();
        }
    }

    private void forgeIronBars()
    {
        mine.takeCoal(coalDemand);
        mine.takeIronOre(ironOreDemand);
        work();
        ironworks.putIronBars(productionAmount);
    }
}
