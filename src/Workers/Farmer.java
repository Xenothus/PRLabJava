package Workers;

import Workers.Abstract.Worker;
import Workplaces.Farm;

public class Farmer extends Worker {

    private static int ID = 0;
    private final Farm farm = Farm.getInstance();

    public Farmer()
    {
        myID = ID++;

        waterConsumption = 1;
        foodConsumption = 1;
        productionDuration = 1;
        productionAmount = 15;
    }

    public Farmer(int waterConsumption,
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
            sowPlants();
            if (!drink())
                break;
        }

        farm.closeCerealStorage();
        farm.closePotatoesStorage();

        reportWorkFinished();
    }

    private void sowPlants()
    {
        work();
        farm.putCereal(productionAmount);
        farm.putPotatoes(productionAmount);
    }
}
