package Workplaces;

import Workplaces.Abstract.SimpleWorkplace;

public class Ironworks extends SimpleWorkplace {

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
        return super.takeProduct(units);
    }

    public void putIronBars(int units)
    {
        super.putProduct(units);
    }

    public void closeIronBarsStorage()
    {
        super.closeProductStorage();
    }
}
