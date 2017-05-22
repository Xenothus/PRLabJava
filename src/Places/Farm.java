package Places;

public class Farm extends ComplexWorkplace {

    private static class SingletonHelper {
        private static final Farm instance = new Farm();
    }

    private Farm(){}

    public static Farm getInstance()
    {
        return SingletonHelper.instance;
    }

    public int takeCereal(int units)
    {
        return super.takeProduct1(units);
    }

    public void putCereal(int units)
    {
        super.putProduct1(units);
    }

    public int takePotatoes(int units)
    {
        return super.takeProduct2(units);
    }

    public void putPotatoes(int units)
    {
        super.putProduct2(units);
    }
}
