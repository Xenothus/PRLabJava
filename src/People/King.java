package People;

import Places.Army;

public class King extends Person {

    private final Army army = Army.getInstance();

    private int happiness;

    public King()
    {
        waterConsumption = 5;
        foodConsumption = 5;
        productionSpeed = 1;

        happiness = 0;
    }

    public King(int waterConsumption,
                int foodConsumption,
                int initialHappiness)
    {
        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;

        this.happiness = initialHappiness;
    }

    @Override
    public void run()
    {
        System.out.println("King moved into castle");

        while (day.isLasting() && work.isOrdered())
        {
            giveMoralesToArmy();
            eat();
            drink();

            happiness++;
        }
    }

    private void giveMoralesToArmy()
    {
        work();
        army.putMorales(happiness);
    }
}
