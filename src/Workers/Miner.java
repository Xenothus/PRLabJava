package Workers;

import Workers.Abstract.Worker;
import Workplaces.Mine;

public class Miner extends Worker {

    private static int ID = 0;
    private final Mine mine = Mine.getInstance();

    public Miner()
    {
        myID = ID++;

        waterConsumption = 2;
        foodConsumption = 2;
        productionDuration = 1;
        productionAmount = 100;
    }

    public Miner(int waterConsumption,
                 int foodConsumption,
                 int productionDuration,
                 int productionAmount)
    {
        myID = ID++;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.productionDuration = productionDuration;
        this.productionAmount = productionAmount;
    }

    @Override
    public void run()
    {
        reportWorkStarted();

        while (day.isLasting() && work.isOrdered())
        {
            collectCoal();
            if (!satisfyAllNeeds())
                break;

            collectIronOre();
            if (!satisfyAllNeeds())
                break;
        }

        mine.closeCoalStorage();
        mine.closeIronOreStorage();

        reportWorkFinished();
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
