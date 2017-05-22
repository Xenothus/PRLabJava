package Places;

public class Ironworks extends ComplexWorkplace {

    private static class SingletonHelper {
        private static final Ironworks instance = new Ironworks();
    }

    private Ironworks()
    {
        super.init();
    }

    public static Ironworks getInstance()
    {
        return SingletonHelper.instance;
    }

    public int takeIronBars(int units)
    {
        return super.takeProduct1(units);
    }

    public void putIronBars(int units)
    {
        super.putProduct1(units);
    }
}
