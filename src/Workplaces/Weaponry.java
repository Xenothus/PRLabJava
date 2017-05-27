package Workplaces;

import Workplaces.Abstract.SimpleWorkplace;

public class Weaponry extends SimpleWorkplace {

    private static class SingletonHelper {
        private static final Weaponry instance = new Weaponry();
    }

    private Weaponry()
    {
        super.init();
    }

    public static Weaponry getInstance()
    {
        return SingletonHelper.instance;
    }


    public int takeWeapons(int units)
    {
        return super.takeProduct(units);
    }

    public void putWeapons(int units)
    {
        super.putProduct(units);
    }

    public void closeWeaponsStorage()
    {
        super.closeProductStorage();
    }
}
