package Main;

import People.*;

import java.util.LinkedList;
import java.util.List;


public class MainApp {

    public static void main (String args[])
    {
        System.out.println("Welcome to our Kingdom!");

        //new Thread(new King()).start();
        //new Thread(War.getInstance()).start();
        //new Thread(new Knight()).start();

        new Thread(Day.getInstance()).start();

        new Thread(new Farmer(1,1,1,20)).start();
        new Thread(new WaterCollector(1,1,1,20)).start();

        new Thread(new Miner(1,1,1, 1)).start();
        new Thread(new IronWorker(1,1,1,1,10,10)).start();
        //new Thread(new Weaponsmith()).start();
    }
}
