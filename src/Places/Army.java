package Places;

public class Army extends ComplexWorkplace {

    private static class SingletonHelper {
        private static final Army instance = new Army();
    }

    private Army()
    {
        super.init();
    }

    public static Army getInstance()
    {
        return SingletonHelper.instance;
    }

    public void setMorales(int units)
    {
        super.setProduct1StorageValue(units);
    }

    public int getMorales()
    {
        return super.getProduct1StorageValue();
    }

    public void putStrength(int units)
    {
        super.putProduct2(units);
    }

    public int getStrength()
    {
        return super.getProduct2StorageValue();
    }
}
