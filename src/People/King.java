package People;

import Places.Army;

public class King extends Person {

    private final Army army = Army.getInstance();

    private int happiness;      // percentage
    private int happinessStep;

    public King()
    {
        waterConsumption = 5;
        foodConsumption = 5;
        productionSpeed = 1;

        happiness = 0;
        happinessStep = 1;
    }

    public King(int waterConsumption,
                int foodConsumption,
                int initialHappiness,
                int happinessPerLoop)
    {
        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;

        this.happiness = initialHappiness;
        this.happinessStep = happinessPerLoop;
    }

    @Override
    public void run()
    {
        System.out.println("King moved into castle");

        while (day.isLasting() && work.isOrdered())
        {
            giveMoralesToArmy();
            if (!satisfyAllNeeds())
                break;

            happiness = (happiness < 100) ? happiness + happinessStep : 100;
        }
    }

    private void giveMoralesToArmy()
    {
        work();
        army.setMorales(happiness);
    }
}
