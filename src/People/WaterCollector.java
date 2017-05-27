package People;

import Places.Well;

public class WaterCollector extends Person {

    private static int ID = 0;
    private final Well well = Well.getInstance();

    public WaterCollector()
    {
        myID = ID++;

        waterConsumption = 1;
        foodConsumption = 1;
        productionSpeed = 1;
        productionAmount = 100;
    }

    public WaterCollector(int waterConsumption,
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
            collectWater();
            eat();
        }

        well.closeStorage();
        System.out.println("Watore End work");
    }

    private void collectWater()
    {
        work();
        well.putWater(productionAmount);
    }
}
