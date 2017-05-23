package Main;

import People.*;

import java.util.LinkedList;
import java.util.List;


public class MainApp {

    public static void main (String args[])
    {
        System.out.println("Welcome to our Kingdom!");

        new Thread(new King()).start();
        new Thread(War.getInstance()).start();
        new Thread(new Knight()).start();

        new Thread(new Farmer()).start();
        new Thread(new WaterCollector()).start();

        new Thread(new Miner()).start();
        new Thread(new IronWorker()).start();
        new Thread(new Weaponsmith()).start();
    }
}
