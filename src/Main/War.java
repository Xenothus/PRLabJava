package Main;

import Places.Army;

public class War implements Runnable {

    private final Army army = Army.getInstance();

    private double battleDuration;  // in seconds
    private int power;              // in units
    private int enemyPower;         // in units

    private static class SingletonHelper {
        private static final War instance = new War();
    }

    private War()
    {
        battleDuration = 1;
        power = 0;
        enemyPower = 100;
    }

    public static War getInstance()
    {
        return SingletonHelper.instance;
    }

    @Override
    public void run()
    {
        System.out.println("War started");

        while (true)
        {
            fight();
            System.out.println("Army power: " + countPower());

            if (power > enemyPower)
            {
                System.out.println("War won");
                WorkInfo.getInstance().endWorkOrder();
                break;
            }
        }
    }

    private void fight()
    {
        try
        {
            Thread.sleep((int)(battleDuration * 1000));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private int countPower()
    {
        return power = (int)(((double)army.getMorales() / 100.0) * (double)army.getStrength());
    }
}
