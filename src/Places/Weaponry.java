package Places;

public class Weaponry extends Workplace {

    private static class SingletonHelper {
        private static final Weaponry instance = new Weaponry();
    }

    private Weaponry(){}

    public static Weaponry getInstance()
    {
        return SingletonHelper.instance;
    }

    public int takeWeapons(int units)
    {
        return super.takeProduct1(units);
    }

    public void putWeapons(int units)
    {
        super.putProduct1(units);
    }
}
