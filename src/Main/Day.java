package Main;

public class Day implements Runnable {

    private final DayInfo day = DayInfo.getInstance();
    private double duration;   // in seconds

    private static class SingletonHelper {
        private static final Day instance = new Day();
    }

    private Day()
    {
        duration = 10;
    }

    public static Day getInstance()
    {
        return SingletonHelper.instance;
    }

    @Override
    public void run()
    {
        try
        {
            Thread.sleep((int)(duration * 1000));
            day.dayEnded();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
