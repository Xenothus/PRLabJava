package Workplaces;

import Workplaces.Abstract.ComplexWorkplace;

public class Mine extends ComplexWorkplace {

    private static class SingletonHelper {
        private static final Mine instance = new Mine();
    }

    private Mine()
    {
        super.init();
        product1Name = "Coal";
        product2Name = "Iron Ore";
    }

    public static Mine getInstance()
    {
        return SingletonHelper.instance;
    }


    public int takeCoal(int units)
    {
        return super.takeProduct1(units);
    }

    public int takeIronOre(int units)
    {
        return super.takeProduct2(units);
    }


    public void putCoal(int units)
    {
        super.putProduct1(units);
    }

    public void putIronOre(int units)
    {
        super.putProduct2(units);
    }


    public void closeCoalStorage()
    {
        super.closeProduct1Storage();
    }

    public void closeIronOreStorage()
    {
        super.closeProduct2Storage();
    }
}
