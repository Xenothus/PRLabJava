package People;

import Places.Ironworks;

public class IronWorker extends Person {

    private static int ID = 0;
    private final Ironworks ironworks = Ironworks.getInstance();

    public IronWorker()
    {
        myID = ID++;

        waterConsumption = 1;
        foodConsumption = 1;
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
        work();
        ironworks.putIronBars(productionAmount);
    }
}
