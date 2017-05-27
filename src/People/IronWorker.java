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
        productionDuration = 1;
        productionAmount = 100;

        coalDemand = 1;
        ironOreDemand = 1;
    }

    public IronWorker(int waterConsumption,
                      int foodConsumption,
                      int productionDuration,
                      int productionAmount,
                      int coalDemand,
                      int ironOreDemand)
    {
        myID = ID++;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
        this.productionDuration = productionDuration;
        this.productionAmount = productionAmount;

        this.coalDemand = coalDemand;
        this.ironOreDemand = ironOreDemand;
    }

    @Override
    public void run()
    {
        reportWorkStarted();

        while (day.isLasting() && work.isOrdered())
        {
            forgeIronBars();
            if (!satisfyAllNeeds())
                break;

            System.out.println("OK");
        }

        ironworks.closeIronBarsStorage();

        reportWorkFinished();
    }

    private void forgeIronBars()
    {
        if (mine.takeCoal(coalDemand) == 0 ||
            mine.takeIronOre(ironOreDemand) == 0)
            return;

        work();
        ironworks.putIronBars(productionAmount);
    }

    //Pomysł: work zrobic tak, ze moze sie nie udac - zwraca boola, ktorego sprawdza putIronBars
}
