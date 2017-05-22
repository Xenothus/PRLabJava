package People;

import Places.Farm;

public class Farmer extends Person {

    private static int ID = 0;
    private final Farm farm = Farm.getInstance();

    public Farmer()
    {
        myID = ID++;

        waterConsumption = 1;
        foodConsumption = 1;
        productionSpeed = 1;
        productionAmount = 100;
    }

    public Farmer(int waterConsumption,
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
            sowPlants();
            eat();
            drink();
        }
    }

    private void sowPlants()
    {
        work();
        farm.putCereal(productionAmount);
        farm.putPotatoes(productionAmount);
    }
}
