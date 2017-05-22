package People;

import Places.Army;

public class King extends Person {

    private final Army army = Army.getInstance();

    private int happiness;

    public King()
    {
        happiness = 0;

        waterConsumption = 5;
        foodConsumption = 5;
    }

    public King(int initialHappiness,
                int waterConsumption,
                int foodConsumption)
    {
        this.happiness = initialHappiness;

        this.waterConsumption = waterConsumption;
        this.foodConsumption = foodConsumption;
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
