package Places;

public class Well extends SimpleWorkplace {

    private static class SingletonHelper {
        private static final Well instance = new Well();
    }

    private Well()
    {
        super.init();
    }

    public static Well getInstance()
    {
        return SingletonHelper.instance;
    }

    public int takeWater(int units)
    {
        return super.takeProduct(units);
    }

    public void putWater(int units)
    {
        super.putProduct(units);
    }
}
