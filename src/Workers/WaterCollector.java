package Workers;

import Workers.Abstract.Worker;
import Workplaces.Well;

public class WaterCollector extends Worker {

    private static int ID = 0;
    private final Well well = Well.getInstance();

    public WaterCollector()
    {
        myID = ID++;

        waterConsumption = 1;
        foodConsumption = 1;
        productionDuration = 1;
        productionAmount = 100;
    }

    public WaterCollector(int waterConsumption,
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
            collectWater();
            if (!eat())
                break;
        }

        well.closeWaterStorage();

        reportWorkFinished();
    }

    private void collectWater()
    {
        work();
        well.putWater(productionAmount);
    }
}
